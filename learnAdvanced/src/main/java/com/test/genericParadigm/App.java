package com.test.genericParadigm;


import javafx.util.Pair;

public class App {
    /**
     * 泛型类
     */
    public static void test_classGeneric(){
        class Box<T>{
            private T t;

            public T getT() {
                return t;
            }

            public void setT(T t) {
                this.t = t;
            }
        }

        Box<Integer> integerBox = new Box<>();
        integerBox.setT(12);
        System.out.println(integerBox.getT());
    }


    public static void main(String[] args) {
        test_classGeneric();

    }
}
