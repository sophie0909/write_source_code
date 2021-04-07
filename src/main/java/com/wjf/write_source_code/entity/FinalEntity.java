package com.wjf.write_source_code.entity;

/**
 * 类被final修饰该类不能被继承
 */
public final class FinalEntity {
    //final 修饰的对象必须先初始化
    /**
     * 对象被final修饰 该对象不可变 对象不可变 引用可变
     */
    private final int finalField=0;

    /**
     * 方法被 final修饰 该方法不能被重写
     */
    public final void say(){
        System.out.println("Hello world");
    }

    public static void main(String[] args) {
//        FinalEntity finalEntity=new FinalEntity();
//        int a=finalEntity.finalField;
//        a=10;
//
//        final FinalEntity b=new FinalEntity();
        System.out.println(AccountType.FIXED);

        // 此处报错  不能给一个final对象赋值 finalEntity.finalFiled=1;
    }
//    class FinalChildClass extends FinalEntity{
//        private int childField;
//
//    }
}
