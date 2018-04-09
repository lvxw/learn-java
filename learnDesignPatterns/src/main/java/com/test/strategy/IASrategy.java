package com.test.strategy;

/**
 * 具体策略类IASrategy
 */
public class IASrategy implements Strategy {
    @Override
    public void say(String msg) {
        System.out.println("a say"+msg);
    }
}
