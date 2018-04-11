package com.test.Responsibility;

/**
 * 测试
 */
public class App {
    public static void main(String[] args) {
        Approver director = new Director("张三");
        Approver president = new President("李四");
        Approver congress = new Congress("meetting");
        director.setApprover(president);
        president.setApprover(congress);

        director.approvalOrder(new Order(100002, 40000));
        director.approvalOrder(new Order(100003, 400000));
        director.approvalOrder(new Order(100004, 900000));

    }
}
