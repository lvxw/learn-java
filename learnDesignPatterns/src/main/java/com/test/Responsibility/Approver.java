package com.test.Responsibility;

/**
 * 审批者抽象类
 */
public abstract  class Approver {
    protected String name;
    protected Approver approver;

    public Approver(String name) {
        this.name = name;
    }

    public void setApprover(Approver approver) {
        this.approver = approver;
    }

    public abstract void approvalOrder(Order order);
}
