package com.test.Responsibility;

/**
 * 董事长审批具体实现类
 */
public class President extends Approver{

    public President(String name) {
        super(name);
    }

    public void approvalOrder(Order order) {
        if(order.getAmount()<500000){
            System.out.println("董事长"+this.name+"审批订单:\t订单号"+order.getOddNumbers()+",订单金额"+order.getAmount());
        }else{
            this.approver.approvalOrder(order);
        }
    }
}
