package com.wjf.write_source_code.juc.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class TicketThread implements Runnable {
    private AtomicInteger count = new AtomicInteger(10000);

    @Override
    public void run() {
        while (count.get()  > 0) {
            try {
                Thread.sleep(30);
            } catch (Exception e) {

            }

            ticket();
        }
    }


    private void ticket() {
        if (count.get()  > 0) {
            System.out.println(Thread.currentThread().getName() + ",正在开始出售:" + (10000 - count.get() + 1));
            count.getAndDecrement();
        }

    }

    public static void main(String[] args) {
        TicketThread ticketThread = new TicketThread();
        for (int i = 1; i <=100 ; i++) {
            Thread thread = new Thread(ticketThread, "窗口"+i);
            thread.start();
        }

    }
}
