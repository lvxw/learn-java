package com.test.factory.simplefactory;

        import com.test.factory.entry.ICar;

/**
 * 测试
 */
public class Main {
    public static void main(String[] args) {
        ICar car = SimpleCarFacory.createCar("spot");
        car.printInfo();
    }
}
