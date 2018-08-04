package com.fg7.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "PURCHASE_ORDER")
public class PurchaseOrder {

    public PurchaseOrder() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

//    @Column(name = "customer_id", nullable = false)
    private long customerId;

//    @Column(name = "item", nullable = false)
    private String item;

//    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "id=" + orderId +
                ", customerId=" + customerId +
                ", item='" + item + '\'' +
                ", localDate=" + orderDate +
                '}';
    }
}
