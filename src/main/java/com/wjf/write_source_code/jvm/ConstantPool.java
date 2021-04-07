package com.wjf.write_source_code.jvm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ConstantPool {
    public static void main(String[] args) throws InterruptedException {
        List<String> list=new LinkedList<>();
        for (int i = 0; i <99999999; i++) {
            String a=i+"";
            System.out.println("=============="+i);
           list.add(a);
        }
        Thread.sleep(3000);
    }

}
