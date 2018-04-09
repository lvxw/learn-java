package com.test.strategy;

/**
 * 具体策略类IBSrategy
 */
public class IBSrategy implements Strategy {
    @Override
    public void say(String msg) {
        System.out.println("b say"+msg);
    }
}
