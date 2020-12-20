package com.wjf.write_source_code.boomFilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;

public class WriteBoomFilter {

    private static Integer n=20000;

    private static double fpp=0.01D;


    //二进制向量大小
    private long numBits;
    //哈希算法数量
    private int numHashFunctions;
    //redis中的key
    private final String key = "goods_filter";
    @Autowired
    private RedisTemplate redisTemplate;


    //向布隆过滤器中put
    public void put(String id){
        long[] indexs = getIndexs(id);
        //将对应下标改为1
        for (long index : indexs) {
            redisTemplate.opsForValue().setBit(key, index, true);
        }
    }

    @PostConstruct
    private void init(){
        numBits = optimalNumOfBits(numBits,fpp);
        numHashFunctions = optimalNumOfHashFunctions(n,numBits);
        //数据重建
        for (int i = 0; i < 10000; i++) {
            put(String.valueOf(i+""));
        }
    }


    //判断id是否可能存在
    public boolean isExist(String id){
        long[] indexs = getIndexs(id);
        //只要有一个bit位为1就表示可能存在
        for (long index : indexs) {
            if (redisTemplate.opsForValue().getBit(key, index)) {
                return true;
            }
        }
        return false;
    }

    //根据key获取bitmap下标(算法借鉴)
    private long[] getIndexs(String key) {
        long hash1 = hash(key);
        long hash2 = hash1 >>> 16;
        long[] result = new long[numHashFunctions];
        for (int i = 0; i < numHashFunctions; i++) {
            long combinedHash = hash1 + i * hash2;
            if (combinedHash < 0) {
                combinedHash = ~combinedHash;
            }
            result[i] = combinedHash % numBits;
        }
        return result;
    }

    //计算哈希值(算法借鉴)
    private long hash(String key) {
        Charset charset = Charset.defaultCharset();
        return Hashing.murmur3_128().hashObject(key, Funnels.stringFunnel(charset)).asLong();
    }



    /**
     * 通过 要存入的元素个数n，和最低错误率p 来计算数组的长度
     * @param n
     * @param p
     * @return
     */
    static long optimalNumOfBits(long n, double p) {
        if (p == 0.0D) {
            p = 4.9E-324D;
        }

        return (long)((double)(-n) * Math.log(p) / (Math.log(2.0D) * Math.log(2.0D)));
    }

    /**
     * 通过 要存入的元素个数n，和数组长度 来计算hash函数的个数
     * @param n
     * @param m
     * @return
     */
    static int optimalNumOfHashFunctions(long n, long m) {
        return Math.max(1, (int)Math.round((double)m / (double)n * Math.log(2.0D)));
    }

    public static void main(String[] args) {
        BloomFilter
    }
}
