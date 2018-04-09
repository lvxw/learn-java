package com.test.factory.factorymethod;

import com.test.factory.entry.ICar;

/**
 * 测试
 */
public class Main {
    public static void main(String[] args) {
        ICarFacotry factory = new SpotCarFactory();
        ICar car = factory.createCar();
        car.printInfo();
    }
}
