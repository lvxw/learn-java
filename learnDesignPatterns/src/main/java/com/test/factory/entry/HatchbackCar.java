package com.test.factory.entry;

/**
 * 车的具体实现类，掀背车
 */
public class HatchbackCar implements ICar {

    @Override
    public void printInfo() {
        System.out.println("品牌是：掀背车\t价格是："+60+"W");
    }
}
