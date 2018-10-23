package com.test.threads;

public class TestVolatile extends Thread{

    private volatile boolean flag;

    public TestVolatile(boolean flag) {
        this.flag = flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        while(flag){
//            System.out.println(Thread.currentThread().getName());
            int q =3;
            q++;
        }
        System.out.println("the "+Thread.currentThread().getName()+" is over...");
    }

    public static void main(String[] args) throws  Exception{
        TestVolatile obj = new TestVolatile(true);
        obj.start();

        Thread.sleep(1000);

        obj.setFlag(false);
    }

}
