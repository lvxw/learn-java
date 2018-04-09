package com.test.factory.factorymethod;

import com.test.factory.entry.HatchbackCar;
import com.test.factory.entry.ICar;

/**
 * 创建掀背车的具体工厂
 */
public class HatchbackCarFactory implements ICarFacotry {
    @Override
    public ICar createCar() {
        return new HatchbackCar();
    }
}
