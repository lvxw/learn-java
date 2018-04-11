package com.test.Responsibility;

/**
 * 主任审批具体实现类
 */
public class Director extends Approver{

    public Director(String name) {
        super(name);
    }

    public void approvalOrder(Order order) {
        if(order.getAmount()<50000){
            System.out.println("主任"+this.name+"审批订单:\t订单号"+order.getOddNumbers()+",订单金额"+order.getAmount());
        }else{
            this.approver.approvalOrder(order);
        }
    }
}
