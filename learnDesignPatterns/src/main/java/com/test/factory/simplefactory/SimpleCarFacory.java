package com.test.factory.simplefactory;

import com.test.factory.entry.HatchbackCar;
import com.test.factory.entry.ICar;
import com.test.factory.entry.JeepCar;
import com.test.factory.entry.SportCar;

/**
 * 根据不同参数，创建不同类型车辆的简单工厂
 */
public class SimpleCarFacory {

    /*//    此种方法不存在扩张问题和耦合问题
    public static ICar createCar(Class cls){
        ICar car = null;
        try {
            car = (ICar) cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  car;
    }*/

    /**
     * 并不能匹配所有情况，一旦需要其他类型的车，就必须修改此处的代码，不符合“开闭原则”
     * @param name
     * @return
     */
    public static ICar createCar(String name){
        ICar car = null;
        switch(name){
            case "spot":
                car = new SportCar();
                break;
            case "hatchback":
                car = new HatchbackCar();
                break;
            case "jeep":
                car = new JeepCar();
                break;
        }
        return car;
    }
}
