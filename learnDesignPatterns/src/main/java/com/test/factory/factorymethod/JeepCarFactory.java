package com.test.factory.factorymethod;

import com.test.factory.entry.ICar;
import com.test.factory.entry.JeepCar;

/**
 * 创建吉普车的具体工厂
 */
public class JeepCarFactory implements ICarFacotry {
    @Override
    public ICar createCar() {
        return new JeepCar();
    }
}
