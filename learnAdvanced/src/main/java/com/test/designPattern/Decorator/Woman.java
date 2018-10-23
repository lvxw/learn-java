package com.test.designPattern.Decorator;

/**
 * 具体组件
 */
public class Woman implements Person{

    @Override
    public void eat() {
        System.out.println("man is eatting fruit");
    }
}