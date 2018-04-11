package com.test.Responsibility;

/**
 * 订单类
 */
public class Order {
    private int oddNumbers;
    private double amount;

    public Order(int oddNumbers, double amount) {
        this.oddNumbers = oddNumbers;
        this.amount = amount;
    }

    public int getOddNumbers() {
        return oddNumbers;
    }

    public void setOddNumbers(int oddNumbers) {
        this.oddNumbers = oddNumbers;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
