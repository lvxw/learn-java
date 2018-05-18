package com.test.threads;

class App {
    public static void main(String []args) throws Exception{
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();

        /*for(int i =1; i<100; i++){
            System.out.println("********************************");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        Thread.sleep(5000);
        thread.interrupt();
    }
}
