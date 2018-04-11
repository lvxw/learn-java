package com.test.Responsibility;

/**
 * 董事会审批具体实现类
 */
public class Congress extends Approver{

    public Congress(String name) {
        super(name);
    }

    public void approvalOrder(Order order) {
        if(order.getAmount()<1000000){
            System.out.println("董事会"+this.name+"审批订单:\t订单号"+order.getOddNumbers()+",订单金额"+order.getAmount());
        }else{
            this.approver.approvalOrder(order);
        }
    }
}
