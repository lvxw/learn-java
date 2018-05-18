package com.test.threads;

class MyRunnable implements Runnable{

    public void run(){
        for(int i =0; i<10000000;i++){
            System.out.println("thread:"+i);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}