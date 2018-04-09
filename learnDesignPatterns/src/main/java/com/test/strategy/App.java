package com.test.strategy;

/**
 * 测试
 */
public class App {
    public static void main(String[] args) {
        Context context = new Context(new IASrategy());
        context.executeStrategyMethod("hello world");

        Context context2 = new Context(new IBSrategy());
        context2.executeStrategyMethod("hello java");
    }
}