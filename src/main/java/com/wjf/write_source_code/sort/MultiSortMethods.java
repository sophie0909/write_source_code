package com.wjf.write_source_code.sort;

import static java.lang.Integer.compare;

import com.alibaba.fastjson.JSON;


/**
 * @author wjf 2021-01-06
 */
public class MultiSortMethods {

    /**
     * 冒泡排序
     * @param arr
     */
    private static void bubbleSort(int[] arr) {
        int len =arr.length ;
        for(int i = 0; i < len - 1; i++) {
            for(int j = 0; j < len - 1 - i; j++) {
                if(arr[j] > arr[j+1]) {        // 相邻元素两两对比
                    int temp = arr[j+1];        // 元素交换
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                    System.out.print("有效");
                }
                System.out.println("排序中===="+ JSON.toJSONString(arr));
            }
        }
    }


    public static int[] quickSort(int[] r, int low, int high){
        if (low<high){
            int pa = partition(r,low,high);
            quickSort(r,low,pa-1);
            quickSort(r,pa+1,high);
        }
        return r;
    }
    private static int partition(int [] r, int low, int high){
        int pivot = r[low]; //使用 r[low]作为枢轴元素
        while (low<high){ //从两端交替向内扫描
            while(low<high&&compare(r[high],pivot)>=0){
                high--;
            }
            r[low] = r[high]; //将比 pivot 小的元素移向低端
            while(low<high&& compare(r[low],pivot)<=0){
                low++;
            }
            r[high] = r[low]; //将比 pivot 大的元素移向高端
        }
        r[low] = pivot; //设置枢轴
        return low; //返回枢轴元素位置
    }


    /**
     * 插入排序
     * @param array
     * @return
     */
    public static int[] insertionSort(int[] array) {
        if (array.length == 0){
            return array;
        }
        int current;
        for (int i = 0; i < array.length - 1; i++) {
            current = array[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current;
        }
        return array;
    }

    public static void main(String[] args) {
      //  bubbleSort(new int[]{3,8,2,1});
        System.out.println(JSON.toJSONString(quickSort(new int[]{3,8,2,1},0,3)));
     //   System.out.println(JSON.toJSONString(insertionSort(new int[]{3,8,2,1})));
    }
}
