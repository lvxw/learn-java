package com.test.threads;

class App2 {
    public static void main(String []args) throws Exception{
        Thread thread1 = new MyThread("thread1");
        thread1.setPriority(1);
        thread1.start();
//        thread1.join();


        Thread thread2 = new MyThread("thread2");
        thread2.setPriority(10);
        thread2.start();
        thread2.join();

        while (true){
            System.out.println("**********************");
            try {
                Thread.yield();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
