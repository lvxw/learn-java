package com.test.singleton;


/**
 * 单例模式——饿汉模式(线程安全)
 * 此种写法，在类加载时，便创建了类的单实例。
 * 因为类只被装载一次（同一个类装载期），所以只有一个实例；
 * 此种饿汉模式不存在现成安全问题，即多线程环境下也可以保证只有一个实例。
 */
public class Singleton {
    private  Singleton(){

    }
    private static Singleton instance = new Singleton();

    public static Singleton getInstance(){
        return  instance;
    }
}


/**
 * 单例模式——懒汉模式（线程安全）
 * 此种写法，效率很低，99%情况下不需要同步。
 */
class Singleton2 {
    private  Singleton2(){

    }
    private static Singleton2 instance;

    public static synchronized Singleton2 getInstance(){
        if(instance == null){
            instance = new Singleton2();
        }
        return  instance;
    }
}



/**
 * 单例模式——懒汉模式（静态内部类，线程安全）
 */
class Singleton3 {
    private  Singleton3(){

    }

    private static class Singleton3Holder{
        private static Singleton3 instance = new Singleton3();
    }

    public static Singleton3 getInstance(){

        return  Singleton3Holder.instance;
    }

}


/**
 * 单例模式——懒汉模式（枚举，线程安全）
 */
enum Singleton4{
    INSTANCE;
}



/**
 * 单例模式——懒汉模式（双重校验所，线程安全）
 */
class Singleton5{
    private  Singleton5(){

    }

    /**
     * 此处的volatile:
     * 这一块代码，有线程A和线程B，如果A先进入第一个if语句，A线程开始实例化Singleton对象，对象的实例化有三个步骤： 分配内存空间，初始化对象，将内存中的地址赋值给变量。
     * 这里可能会发生重排序，如果这里先执行分配和赋值操作，线程B此时进入第一个if语句，发现变量不为null，直接返回这个实例，然后B线程此时拿到的可能是还没有实例化的对象。
     * 所以用volatile修饰singleton就可以避免这种重排序了。
     */
    private volatile static Singleton5 instance;

    public static Singleton5 getInstance(){
        if(instance == null){
            synchronized (Singleton5.class){
                if(instance == null){
                    instance = new Singleton5();
                }
            }
        }
        return  instance;
    }
}


