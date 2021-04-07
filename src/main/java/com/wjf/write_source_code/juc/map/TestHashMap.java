package com.wjf.write_source_code.juc.map;

import sun.misc.Unsafe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TestHashMap {

    public static void main(String[] args) {
//        HashMap<Integer,String> map1=new HashMap();
//        map1.put(1,"1");
//        map1.put(2,"2");

    /*    HashMap<Integer,String> map1=new HashMap(17);
        map1.put(1,"1");
        map1.put(2,"2");
        map1.put(9,"9");
        System.out.println(1&(16-1));
        map1.remove(1);*/
        ConcurrentHashMap<Integer,String> map=new ConcurrentHashMap<>();
        map.put(1,"1");
        map.put(null,"3");
        map.remove(1);
        List list=new ArrayList();
        list.add(1);
        List list2=new LinkedList();



    }
}
