package com.thread;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyRunnable implements Runnable{
   private static Lock lock = new ReentrantLock();

    @Override
    public void run() {
        lock.lock();
        for (int i = 0; i < 30; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lock.unlock();
    }




    public static void main(String[] args) throws InterruptedException {
        MyRunnable myRunnable = new MyRunnable();
        MyRunnable myRunnable2 = new MyRunnable();

        Thread t1 = new Thread(myRunnable);
        Thread t2 = new Thread(myRunnable2);

//        设置为守护线程，需要在线程启动前设置；主线程结束，守护线程无论是否zhi'xing执行完毕，都自动结束
//        t1.setDaemon(true);
//        t2.setDaemon(true);
//        System.out.println(t1.isDaemon()+"----------------------");
//        t1.setPriority(Thread.MAX_PRIORITY);
//        t2.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();


//        // main线程需要等待t1、t2线程结束才继续执行
//        t1.join();
//        t2.join();

        Thread.sleep(100);

        System.out.println("进程结束");
        System.out.println(Thread.currentThread().getPriority());
        System.out.println(t1.getPriority());
        System.out.println(t2.getPriority());

    }
}
