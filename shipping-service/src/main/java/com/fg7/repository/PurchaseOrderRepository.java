package com.fg7.repository;

import com.fg7.domain.PurchaseOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrder, Long> {

    List<PurchaseOrder> findPurchaseOrdersByOrderId(Long id);

    List<PurchaseOrder> findPurchaseOrdersByOrderIdAndCustomerId(Long orderId, Long customerId);
}
