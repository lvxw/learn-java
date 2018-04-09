package com.test.factory.factorymethod;

import com.test.factory.entry.ICar;
import com.test.factory.entry.SportCar;

/**
 * 创建跑车的具体工厂
 */
public class SpotCarFactory implements ICarFacotry {
    @Override
    public ICar createCar() {
        return new SportCar();
    }
}
