package com.wjf.write_source_code.dataStructureAndAlgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wjf 2021-01-11
 */
public class Solution {

    public String LCS1 (String str1, String str2) {
        if(str1==null||str2==null||str1.isEmpty()||str2.isEmpty()){
            return "-1";
        }
        int m=str1.length();
        int n=str2.length();
        //使用bp[i][j] 用来表示 str1{0->(i-1)} 和str2{0->(j-1)}的公共字符串长度
        int[][] bp=new int[m][n];
        int maxLength=0;
        int maxIndex=0;
        for (int i = 0; i <m ; ++i) {
            for (int j = 0; j <n ; ++j) {
                if(str1.charAt(i)==str2.charAt(j)){
                    if(i==0||j==0){
                        bp[i][j]=1;
                    }else{
                        // bp[i][j] 和bp[i-1][j-1]相比较 str1向后移动一个字符 str2向后移动一个字符
                        bp[i][j]=bp[i-1][j-1]+1;
                    }
                    //
                    if(maxLength<bp[i][j]){
                        maxIndex=i;
                        maxLength=bp[i][j];
                    }

                }
            }
        }
        if(maxLength == 0){
            return "-1";
        }
        return str1.substring(maxIndex-maxLength+1,maxIndex+1);
    }
    /**
     * NC 127
     * 最长公共子串 longest common substring
     * 1、每当有重复字符出现 才可能对重复字符数有影响 str1.charAt(i) == str2.charAt(j)
     * 在二维数组中，在有重复数组出现时，计算出当前的重复字符数
     *2、随着两个字符串循环的推进，重复字符数 可能会变大或者变小，就是出现最长重复字符数的坐标是不固定的 所以添加maxLen < dp[i][j]判断
     *3、添加if(maxLen == 0)判断的原因 str1的长度为1时 str1.substring(indexMax - maxLen  + 1 , indexMax + 1)会出现异常
     * @param str1
     * @param str2
     * @return
     */
    public String LCS (String str1, String str2) {

        if(str1 == null || str2 == null || str1.equals("") || str2.equals("")){
            return "-1";
        }
        int indexMax = 0;
        int maxLen = 0;
        int m = str1.length();
        int n = str2.length();

        //dp[i][j]代表 str1[0~i-1]和str2[0~j-1] 的最长公共子串的长度
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; ++ i){
            for(int j = 0; j < n; ++j){
                if(str1.charAt(i) == str2.charAt(j)){
                    if(i == 0 || j == 0){
                        dp[i][j] = 1;
                    }else{
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                }//else 是str1[i]!=str2[j]的情况,这种情况下dp[i][j]=0,由于初始化已经将其设置为0,所以这里不再写。

                //处理完dp[i][j]之后,查看一下是否需要记录下来
                if(maxLen < dp[i][j]){
                    maxLen = dp[i][j]; //记录下最长公共子串的长度
                    indexMax = i; //记录下出现“最长公共子串”时的末尾字符的位置
                }
            }
        }
        if(maxLen == 0){
            return "-1";
        }
        //字符串截取的长度有(end-start+1) = maxLen, 那么start = indexMax +1-maxLen
        // maxLen即为所截取的字符串的长度。
        return str1.substring(indexMax - maxLen  + 1 , indexMax + 1);

    }


    /*
     * Longest Common SubString最长公共子串 - 结果
     * Longest Common SubSequece最长公共子序列 - 结果
     * Longest Increasing SubSequence最长递增子序列 - 长度 & 结果
     * 无重复字符的最长子串 - 长度
     */

    /**
     * NC 92
     * LC 1143
     * longest common subsequence
     * @param str1 string字符串 the string
     * @param str2 string字符串 the string
     * @return string字符串
     */
    public static String longestCommonSubSequece(String str1,String str2){
        if(str1 == null || str2 == null || str1.equals("") || str2.equals("")){
            return "-1";
        }
       StringBuilder stringBuilder=new StringBuilder();

        int m = str1.length();
        int n = str2.length();
        int[][] dp=new int[m][n];
        for(int i = 0; i < m; ++ i){
            for(int j = 0; j < n; ++j){
                if(str1.charAt(i) == str2.charAt(j)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return "";
    }

    /**
     * NC 91
     * 最长递增子序列
     * retrun the longest increasing subsequence
     * @param arr int整型一维数组 the array
     * @return int整型一维数组
     */
    public int[] LIS (int[] arr) {
        // write code here
        return null;
    }

    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text1.length() <= 0 || text2 == null || text2.length() <= 0) {
            return 0;
        }

        int length1 = text1.length();
        int length2 = text2.length();
        int[][] dp = new int[length1 + 1][length2 + 1];

        /*
            遍历两个字符串:
                1、若 当前两个字符 相等：
                    当前状态 = 两个元素的前一个状态 + 1
                2、若 当前两个字符 不等：
                    当前状态 = 两个元素任一元素的前一个状态 的 最大值
         */
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (chars1[i - 1] == chars2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[length1][length2];
    }


    public static void main(String[] args) {
        System.out.println((short)10/10.2*2);




    }
}
