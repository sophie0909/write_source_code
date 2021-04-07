package com.wjf.write_source_code.thread;

import com.google.common.util.concurrent.RateLimiter;

import java.util.*;
import java.util.concurrent.*;

public class TestMultiThread {
    public static void main(String[] args) {
        CopyOnWriteArrayList
        ThreadPoolExecutor poolExecutor=new ThreadPoolExecutor(4,10,1000,
                TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>());
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i <100 ; i++) {
            list.add(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("线程池中线程数目："+poolExecutor.getPoolSize()+"，队列中等待执行的任务数目："+
                    poolExecutor.getQueue().size()+"，已执行完别的任务数目："+poolExecutor.getCompletedTaskCount());
            poolExecutor.submit(()->{
                Thread thread= Thread.currentThread();
                for (int i1 = 0; i1 < list.size(); i1++) {
                    System.out.println(thread.getName()+":"+list.get(i1));
                }

            });

        }

    }



}
