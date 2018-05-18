package com.test.genericParadigm;

class Box2<T> {
    // T stands for "Type"
    private T t;
    public void set(T t) { this.t = t; }
    public T get() { return t; }
}

class Myutil3{
    public static void testBox(Box2<? extends Number> box){
        System.out.println(box.get());
    }
}

public class App4 {

    public static void main(String[] args) {
        Myutil3.testBox(new Box2<Integer>());
        Myutil3.testBox(new Box2<Number>());
    }
}
