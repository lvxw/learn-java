package com.thread;


import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for(int i=1; i<=100; i++){
            sum += i;
            Thread.sleep(10);
        }
        return sum;
    }


    public static void main(String[] args) throws Exception {
        MyCallable myCallable = new MyCallable();
        FutureTask<Integer> futureTask = new FutureTask<>(myCallable);

        Thread t = new Thread(futureTask);
        t.start();

        Integer result = futureTask.get();

        System.out.println(result);
    }
}
