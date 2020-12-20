package com.wjf.write_source_code.enumpack;

public class Test {
    public static void main(String[] args) {
        System.out.println(SimpleEnum.YES);
        System.out.println(MultiParamEnum.soc_payment.isAddValid());
        System.out.println(MultiParamEnum.valueOf("soc").getProcessCode());
    }
}
