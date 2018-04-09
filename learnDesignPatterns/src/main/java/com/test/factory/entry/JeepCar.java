package com.test.factory.entry;

/**
 * 车的具体实现类，吉普车
 */
public class JeepCar implements ICar {
    @Override
    public void printInfo() {
        System.out.println("品牌是：吉普车\t价格是："+20+"W");
    }
}
