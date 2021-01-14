package com.wjf.write_source_code.dataStructureAndAlgorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Solution
 * @Description todo
 * @Author wjf
 * @Date 2020/6/5 16:45
 * @Version V1.0
 **/
public class Solution {


    /**
     * 1. 两数之和
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     * 示例:
     *
     * 给定 nums = [2, 7, 11, 15], target = 9
     *
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int compoment=target-nums[i];
            if(map.containsKey(compoment)){
                return new int[]{i,map.get(compoment)};
            }
            map.put(nums[i],i);
        }
        throw new IllegalArgumentException("no towSum solution");
    }

    /**
     * 15.三数之和
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     *
     * 注意：答案中不可以包含重复的三元组。
     * 示例：
     *
     * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     *
     * 满足要求的三元组集合为：
     * [
     *   [-1, 0, 1],
     *   [-1, -1, 2]
     * ]
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> solutionList=new ArrayList<>();
        int len=nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]>0){break; }
            if(i>0&&nums[i]==nums[i-1]){continue;}
            int L=i+1;
            int R=len-1;
            if (L>R) {break;}
            while (L<R){
                int sum=nums[i]+nums[L]+nums[R];
                if(sum==0){
                    solutionList.add(Arrays.asList(nums[i],nums[L],nums[R]));
                    while(L<R&&nums[L]==nums[L+1]){L++;}
                    while(L<R&&nums[R]==nums[R-1]){R++;}
                    L++;
                    R--;
                }else if (sum < 0) {
                    L++;
                }
                else if (sum > 0) {
                    R--;
                }
            }
        }
        return solutionList;
    }

    /**
     * 18. 四数之和
     * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
     * 注意：
     * 答案中不可以包含重复的四元组。
     * 示例：
     * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
     *
     * 满足要求的四元组集合为：
     * [
     *   [-1,  0, 0, 1],
     *   [-2, -1, 1, 2],
     *   [-2,  0, 0, 2]
     * ]
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        /*定义一个返回值*/
        List<List<Integer>> result=new ArrayList<>();
        /*当数组为null或元素小于4个时，直接返回*/
        if(nums==null||nums.length<4){
            return result;
        }
        /*对数组进行从小到大排序*/
        Arrays.sort(nums);
        /*数组长度*/
        int length=nums.length;
        /*定义4个指针k，i，j，h  k从0开始遍历，i从k+1开始遍历，留下j和h，j指向i+1，h指向数组最大值*/
        for(int k=0;k<length-3;k++){
            /*当k的值与前面的值相等时忽略*/
            if(k>0&&nums[k]==nums[k-1]){
                continue;
            }
            /*获取当前最小值，如果最小值比目标值大，说明后面越来越大的值根本没戏*/
            int min1=nums[k]+nums[k+1]+nums[k+2]+nums[k+3];
            if(min1>target){
                break;
            }
            /*获取当前最大值，如果最大值比目标值小，说明后面越来越小的值根本没戏，忽略*/
            int max1=nums[k]+nums[length-1]+nums[length-2]+nums[length-3];
            if(max1<target){
                continue;
            }
            /*第二层循环i，初始值指向k+1*/
            for(int i=k+1;i<length-2;i++){
                /*当i的值与前面的值相等时忽略*/
                if(i>k+1&&nums[i]==nums[i-1]){
                    continue;
                }
                /*定义指针j指向i+1*/
                int j=i+1;
                /*定义指针h指向数组末尾*/
                int h=length-1;
                /*获取当前最小值，如果最小值比目标值大，说明后面越来越大的值根本没戏，忽略*/
                int min=nums[k]+nums[i]+nums[j]+nums[j+1];
                if(min>target){
                    continue;
                }
                /*获取当前最大值，如果最大值比目标值小，说明后面越来越小的值根本没戏，忽略*/
                int max=nums[k]+nums[i]+nums[h]+nums[h-1];
                if(max<target){
                    continue;
                }
                /*开始j指针和h指针的表演，计算当前和，如果等于目标值，j++并去重，h--并去重，当当前和大于目标值时h--，当当前和小于目标值时j++*/
                while (j<h){
                    int curr=nums[k]+nums[i]+nums[j]+nums[h];
                    if(curr==target){
                        result.add(Arrays.asList(nums[k],nums[i],nums[j],nums[h]));
                        j++;
                        while(j<h&&nums[j]==nums[j-1]){
                            j++;
                        }
                        h--;
                        while(j<h&&i<h&&nums[h]==nums[h+1]){
                            h--;
                        }
                    }else if(curr>target){
                        h--;
                    }else {
                        j++;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 16. 最接近的三数之和
     * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
     * 示例：
     *
     * 输入：nums = [-1,2,1,-4], target = 1
     * 输出：2
     * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            throw new IllegalArgumentException("no solution result");
        }
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length-3; i++) {
            int start = i + 1, end = nums.length - 1;
            while (start < end) {
                int sum = nums[start] + nums[end] + nums[i];
                if (Math.abs(target - sum) < Math.abs(target - ans)) {
                    ans = sum;
                }
                if (sum > target) {
                    end--;
                } else if (sum < target) {
                    start++;
                } else {
                    return ans;
                }
            }
        }
        return ans;
    }

    /**
     *面试题 16.06. 最小差
     * 给定两个整数数组a和b，计算具有最小差绝对值的一对数值（每个数组中取一个值），并返回该对数值的差
     * 示例：
     * 输入：{1, 3, 15, 11, 2}, {23, 127, 235, 19, 8}
     * 输出： 3，即数值对(11, 8)
     * 提示：
     *
     * 1 <= a.length, b.length <= 100000
     * -2147483648 <= a[i], b[i] <= 2147483647
     * 正确结果在区间[-2147483648, 2147483647]内
     * @param a
     * @param b
     * @return
     */

    public int smallestDifference(int[] a, int[] b) {
        int ans=Math.min(Math.abs(a[0]-b[0]),Math.abs(b[0]-a[0]));
        for (int i = 0; i < a.length; i++) {
            for (int i1 = 0; i1 < b.length; i1++) {
                int temp=Math.min(Math.abs(a[i]-b[i1]),Math.abs(b[i1]-a[i]));
                if(temp<ans){
                    ans=temp;
                }
            }
        }

        return ans;

    }


    /**
     * 面试题 17.05.  字母与数字
     * 给定一个放有字符和数字的数组，找到最长的子数组，且包含的字符和数字的个数相同。
     *
     * 返回该子数组，若存在多个最长子数组，返回左端点最小的。若不存在这样的数组，返回一个空数组。
     *
     * 示例 1:
     *
     * 输入: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7","H","I","J","K","L","M"]
     *
     * 输出: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7"]
     * 示例 2:
     *
     * 输入: ["A","A"]
     *
     * 输出: []
     * array.length <= 100000
     * @param array
     */

    public String[] findLongestSubarray(String[] array) {
        int charNum=0;
        int numNum=0;
        for (String s : array) {
            char i=s.charAt(0);
            if ((i>=65&&i<=90)||(i>=97&&i<=122)){
                charNum++;
            }else{
                numNum++;
            }
        }
        System.out.println("charNum========"+charNum);
        System.out.println("numNum========"+numNum);

        int index=Math.min(charNum,numNum);
        int charIndex=0;
        int numIndex=0;
        List<String> list=new LinkedList<>();
        for (String s : array) {
            char i=s.charAt(0);
             if(charIndex>=index&&numIndex>=index){
                 return list.toArray(new String[list.size()]);
             }
            if ((i>=65&&i<=90)||(i>=97&&i<=122)){
                if(charIndex>=index){
                    continue;
                }
                    charIndex++;
                    list.add(s);
            }else{
                if(numIndex>=index){
                    continue;
                }
                    numIndex++;
                    list.add(s);

            }

        }
        return list.toArray(new String[list.size()]);
    }
    public static void main(String[] args) {

      //  System.out.println(new Solution().findLongestSubarray(new String[]{"A","1","B","C","D","2","3","4","E","5","F","G","6","7","H","I","J","K","L","M"}));
        String[] strArr=new Solution().findLongestSubarray(new String[]{"A","1","B","C","D","2","3","4","E","5","F","G","6","7","H","I","J","K","L","M"});
        for (String s : strArr) {
            System.out.print(s+",");
        }
//        int[] arr=new int[]{-1,-1,0,1,2,-4};
//
//        Arrays.sort(arr);
//        System.out.println(JSON.toJSONString(new Solution().fourSum(arr,-1)));
      //  System.out.println(JSON.toJSONString(new Solution().twoSum(arr,5)));
        // -4,-1,-1,0,1,2


    }
}
