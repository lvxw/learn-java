package com.test.factory.entry;

/**
 * 车的具体实现类，跑车
 */
public class SportCar implements ICar {

    @Override
    public void printInfo() {
        System.out.println("品牌是：跑车\t价格是："+40+"W");
    }
}
