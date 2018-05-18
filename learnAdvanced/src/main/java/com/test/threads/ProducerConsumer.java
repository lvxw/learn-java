package com.test.threads;


class MoMo{
    private String id;

    public MoMo(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MoMo{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}

class SyncStack{
    int index = 0;
    MoMo[] bucket = new MoMo[10];

    public synchronized void push(MoMo moMo){
        if(index == 10){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }else{
            this.notifyAll();
            bucket[index] = moMo;
            index ++;
            System.out.println(moMo+"被"+Thread.currentThread().getName()+"放入了篮子");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public synchronized void pop(){
        if(index == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            this.notifyAll();
            index --;
            System.out.println(bucket[index]+"被"+Thread.currentThread().getName()+"取走");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class producer extends Thread{
    SyncStack stack;

    public producer(SyncStack stack,String name) {
        super(name);
        this.stack = stack;
    }

    @Override
    public void run() {
        for(int i=0; i<2000000000; i++){
            String prefix = Thread.currentThread().getName();
            MoMo moMo = new MoMo(prefix+":"+i);
            stack.push(moMo);
        }
    }
}

class Consumer extends Thread{
    SyncStack stack;

    public Consumer(SyncStack stack,String name) {
        super(name);
        this.stack = stack;
    }

    @Override
    public void run() {
        for(int i=0; i<2000000000; i++){
            stack.pop();
        }
    }
}


public class ProducerConsumer {
    public static void main(String[] args) {
        SyncStack syncStack = new SyncStack();
        producer producer1 = new producer(syncStack,"producer1");
        producer1.start();
        producer producer2 = new producer(syncStack,"producer2");
        producer2.start();

        Consumer consumer1 = new Consumer(syncStack,"consumer1");
        consumer1.start();

        Consumer consumer2 = new Consumer(syncStack,"consumer2");
        consumer2.start();
    }
}
