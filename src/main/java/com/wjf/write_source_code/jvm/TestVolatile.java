package com.wjf.write_source_code.jvm;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestVolatile extends Thread {

    private static volatile int count;

    public static void create() {
        count++;
    }

    public void testCountLatch(int num){
        if(num<1){
            return;
        }
        CountDownLatch countDownLatch=new CountDownLatch(num);
        for (int i = 0; i < num; i++) {
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+"===start");
                   Thread.sleep(1000);
                    countDownLatch.await();
                    System.out.println(Thread.currentThread().getName()+"===end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            ).start();
            countDownLatch.countDown();
        }
    }

    public void testSemaphore(int num) {
        Semaphore semaphore = new Semaphore(num);
        for (int i = 0; i < num+3; i++) {
            new Thread(() -> {
                try {
                    // 获取凭证 状态-1
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ",抢到了凭据");
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + ",释放凭据");
                    // 状态+1
                    semaphore.release();
                } catch (Exception e) {

                }

            }).start();
        }
    }
    public void testCyclicBarrier(int num) {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(num);
        for (int i = 0; i < num+3; i++) {
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+"===start");
                    Thread.sleep(1000);
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName()+"===end");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            ).start();
        }
    }
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    public void testCondition(int num){
        Thread thread = new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + ":1");
                condition.await();
                System.out.println(Thread.currentThread().getName() + ":2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (Exception e) {

        }
        lock.lock();
        // 唤醒
        condition.signal();
        lock.unlock(); //lock锁需要显示的释放，不释放的话，该段程序会一直阻塞下去，线程不会被释放
        System.out.println(Thread.currentThread().getName() + ":3");
    }
    public static void main(String[] args) {
//        ArrayList<Thread> threads = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            Thread tempThread = new Thread(() -> {
//                for (int j = 0; j < 1000; j++) {
//                    create();
//                }
//            });
//            threads.add(tempThread);
//            tempThread.start();
//        }
//        threads.forEach(thread -> {
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        System.out.println(count);
     //   new TestVolatile().testCountLatch(3);
     //   new TestVolatile().testSemaphore(2);
     //   new TestVolatile().testCyclicBarrier(3000);
        new TestVolatile().testCondition(4);
    }


}
