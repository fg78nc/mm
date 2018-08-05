package com.fg7.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "PURCHASE_ORDER")
public class PurchaseOrder implements Serializable {

    private static final long serialVersionUID = -2568426453250186608L;

    public PurchaseOrder() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    @Column(name = "customer_id", nullable = false)
    private long customerId;

    @Column(name = "item", nullable = false)
    private String item;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;


    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "id=" + orderId +
                ", customerId=" + customerId +
                ", item='" + item + '\'' +
                ", localDate="  + orderDate +
                '}';
    }
}
