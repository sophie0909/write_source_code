package com.wjf.write_source_code.juc.thread;

public class CreateThread {

//    private static ThreadLocal threadLocal = new ThreadLocal<String>();
//
//    public static void main(String[] args) {
//        threadLocal.set("mayikt");
//        threadLocal.remove();
//        threadLocal = null;
//        System.gc();
//
//        Thread thread = Thread.currentThread();
//        threadLocal.get();
//    }


//    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
//
//    public static void main(String[] args) throws InterruptedException {
//        Thread thread = new Thread(() -> {
//            threadLocal.set("每特教育666");
//            System.out.println("成功设置threadLocal");
//
//        });
//        thread.start();
//        thread.join();
//        System.out.println(Thread.currentThread().getName() + "," + threadLocal.get());
//
//    }

    private String context;

    public String getContext() {
        return threadLocal.get();
//        return context;
    }

    public void setContext(String context) {

//        this.context = context;
        threadLocal.set(context);
    }

    private static Object lock = new Object();
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        CreateThread test004 = new CreateThread();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
//                synchronized (lock) {
                test004.setContext(Thread.currentThread().getName() + "," + finalI);
                try {
                    Thread.sleep(500);
                } catch (Exception e) {

                }
                System.out.println("线程id:"
                        + Thread.currentThread().getName() + "," + test004.getContext());
//                }


            }, i + "").start();
        }
    }


}
