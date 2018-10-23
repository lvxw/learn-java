package com.test.threads;

public class ThreadException{

    public synchronized void operator(){
        int i = 0;
        while (true){
            try {
                Thread.sleep(1000);
                i ++;
                if(i == 10){
                    throw  new Exception();
                }
                System.out.println(Thread.currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ThreadException te = new ThreadException();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                te.operator();
            }
        },"thread1");

        thread1.start();

        Thread.sleep(1000);

        te.operator();


    }
}
