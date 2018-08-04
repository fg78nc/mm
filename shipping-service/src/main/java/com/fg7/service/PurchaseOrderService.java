package com.fg7.service;

import com.fg7.domain.PurchaseOrder;
import com.fg7.repository.PurchaseOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrderService {

    private PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    public List<PurchaseOrder> getOrdersById(Long orderId) {
        return this.purchaseOrderRepository.findPurchaseOrdersByOrderId(orderId);
    }

    public List<PurchaseOrder> getOrderByOrderIdAndCustomerId(Long orderId, Long customerId) {
        return this.purchaseOrderRepository.findPurchaseOrdersByOrderIdAndCustomerId(orderId, customerId);
    }

    public void saveOrder(PurchaseOrder purchaseOrder){
        this.purchaseOrderRepository.save(purchaseOrder);
    }


}
