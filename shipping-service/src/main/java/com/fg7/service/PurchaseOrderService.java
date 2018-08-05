package com.fg7.service;

import com.fg7.domain.Customer;
import com.fg7.domain.PurchaseOrder;
import com.fg7.domain.PurchaseOrderWithCustomerInfo;
import com.fg7.repository.PurchaseOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final CustomerServiceClient customerServiceClient;

    public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository,
                                CustomerServiceClient customerServiceClient) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.customerServiceClient = customerServiceClient;
    }

    public List<PurchaseOrder> getOrdersById(Long orderId) {
        return this.purchaseOrderRepository.findPurchaseOrdersByOrderId(orderId);
    }


    public PurchaseOrder saveOrder(PurchaseOrder purchaseOrder){
        return this.purchaseOrderRepository.save(purchaseOrder);
    }

    public PurchaseOrder getOrderById(Long id){
        return this.purchaseOrderRepository
                .findById(id)
                .orElseThrow(() -> new PurchaseOrderException("Order does not exist"));
    }

    public List<PurchaseOrder> getOrders() {
        return (List<PurchaseOrder>) this.purchaseOrderRepository.findAll();
    }

    public PurchaseOrderWithCustomerInfo getOrdersWithCustomerInfo(Long orderId, Long customerId){
        Customer customer = this.customerServiceClient.getCustomer(customerId);
        PurchaseOrderWithCustomerInfo purchaseOrderWithCustomerInfo = new PurchaseOrderWithCustomerInfo();
        purchaseOrderWithCustomerInfo.setOrderedItem(this.getOrderById(orderId).getItem());
        purchaseOrderWithCustomerInfo.setCustomerFirstName(customer.getFirstName());
        purchaseOrderWithCustomerInfo.setCustomerLastName(customer.getLastName());
        return purchaseOrderWithCustomerInfo;
    }

}
