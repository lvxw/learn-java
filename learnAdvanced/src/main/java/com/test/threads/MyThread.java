package com.test.threads;

public class MyThread extends Thread{
    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (true){
            System.out.println(Thread.currentThread().getName()+":------------------------");
            try {
                Thread.yield();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
