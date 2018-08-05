package com.fg7.web;

import com.fg7.domain.PurchaseOrder;
import com.fg7.domain.PurchaseOrderWithCustomerInfo;
import com.fg7.service.PurchaseOrderService;
import com.fg7.utils.ContextCacheHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("v1/orders")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @GetMapping("/customer/{customerId}/order/{orderId}")
    public PurchaseOrderWithCustomerInfo getOrdersWithCustomerInfo(@PathVariable("orderId") Long orderId,
                                                                   @PathVariable("customerId") Long customerId) {
        log.info("context cache : {}", ContextCacheHolder.getTLContext().getTokenId());
        return this.purchaseOrderService.getOrdersWithCustomerInfo(orderId, customerId);
    }

    @PostMapping("/save")
    public ResponseEntity<?> savePurchaseOrder(@RequestBody PurchaseOrder purchaseOrder){
        log.info("Saving order {}", purchaseOrder);
        final PurchaseOrder newPurchaseOrder = this.purchaseOrderService.saveOrder(purchaseOrder);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("orders/{id}")
                .buildAndExpand(newPurchaseOrder.getOrderId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public PurchaseOrder getPurchaseOrder(@PathVariable("id") long orderId){
        PurchaseOrder order = this.purchaseOrderService.getOrderById(orderId);
        log.info("Retrieved order {}", order);
        return order;
    }

    @GetMapping()
    public List<PurchaseOrder> getPurchaseOrders( ){
        return this.purchaseOrderService.getOrders();
    }




}
