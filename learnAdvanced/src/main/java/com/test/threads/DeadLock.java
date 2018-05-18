package com.test.threads;

public class DeadLock implements Runnable{
    private static Object o1 = new Object();
    private static Object o2 = new Object();
    Boolean flag;

    public DeadLock(Boolean flag) {
        this.flag = flag;

    }

    @Override
    public void run() {
        if(flag){
            lockCode(o1,o2);
        }else{
            lockCode(o2,o1);
        }

    }

    private void lockCode(Object first, Object second){
        synchronized (first){
            System.out.println(Thread.currentThread().getName()+":获得了"+first+"这把锁,还需要获取"+second+"这把锁");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (second){
                System.out.println(Thread.currentThread().getName()+":获得了"+second+"这把锁,还需要获取"+first+"这把锁");
            }
        }
    }

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock(true);
        DeadLock deadLock2 = new DeadLock(false);

        Thread thread1 = new Thread(deadLock, "thread1");
        Thread thread2 = new Thread(deadLock2, "thread2");

        thread1.start();
        thread2.start();
    }
}
