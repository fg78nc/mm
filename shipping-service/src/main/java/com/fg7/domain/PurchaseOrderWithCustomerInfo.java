package com.fg7.domain;

public class PurchaseOrderWithCustomerInfo {

    private String customerFirstName;
    private String customerLastName;
    private String orderedItem;

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getOrderedItem() {
        return orderedItem;
    }

    public void setOrderedItem(String orderedItem) {
        this.orderedItem = orderedItem;
    }

    @Override
    public String toString() {
        return "PurchaseOrderWithCustomerInfo{" +
                "customerFirstName='" + customerFirstName + '\'' +
                ", customerLastName='" + customerLastName + '\'' +
                ", orderedItem='" + orderedItem + '\'' +
                '}';
    }
}
