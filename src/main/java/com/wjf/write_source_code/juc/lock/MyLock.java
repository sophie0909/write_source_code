package com.wjf.write_source_code.juc.lock;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLock {
    public static void main(String[] args) {

        Lock lock = new ReentrantLock();

        Condition c1 = lock.newCondition();

        Semaphore semaphore = new Semaphore(5);

        CountDownLatch countDownLatch = new CountDownLatch(1);
        CyclicBarrier cyclicBarrier=new CyclicBarrier(2);
    }
}
