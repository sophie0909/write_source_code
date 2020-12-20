package com.wjf.write_source_code.collection.list;

import java.util.Arrays;

/**
 * 基于数组的结构实现
 * @param <T>
 */
public class WriteArrayList<T> {
    /**
     * 数组
     */
    Object[] elementData;
    /**
     * 当前我们数组存放的元素个数
     */
    private int size;

    /**
     * elementData 默认初始容量为10
     */
    private static final int DEFAULT_CAPACITY = 10;

    public WriteArrayList() {
    }

    /**
     * 新增元素
     *
     * @param t
     */
    public void add(T t) {
        // 默认的初始化
        if (elementData == null) {
            elementData = new Object[DEFAULT_CAPACITY];
        }
        // 判断是否需要做扩容
        if ((size + 1) > elementData.length) {
            // 原来容量
            int oldCapacity = elementData.length;
            // 新容量 10 +10/2 =15
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            // 扩容
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
        elementData[size++] = t;
    }

    public T get(int index) {
        return (T) elementData[index];
    }

    public boolean remove(T t) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(t)) {
                int numMoved = size - i - 1;
                if (numMoved > 0)
                /**
                 * 被移除的下标的后续位置往前移动一位，最后一位第size-1位置元素执为空
                 */
                    System.arraycopy(elementData, i + 1, elementData, i,
                            numMoved);
                elementData[--size] = null; // clear to let GC do its work
            }
        }

        return false;
    }

    public static void main(String[] args) {
//       int i=Integer.MAX_VALUE;
//        System.out.println(i);
//        ArrayList<Integer> list=new ArrayList<>(1);
//        for (int j = 0; j <20 ; j++) {
//            if(j<9){
//                list.add(j);
//            }
//            else{
//                list.add(j);
//            }
//
//
//            System.out.println("list"+j+"====="+list.toString());
//        }

        WriteArrayList<String> arraylist = new WriteArrayList<>();
        for (int i = 0; i < 100; i++) {
            arraylist.add( i+"");
        }
        arraylist.remove("0");
        for (int i = 0; i < arraylist.size; i++) {
            System.out.println(arraylist.get(i));
        }
    }

}
