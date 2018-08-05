package com.fg7.service;

import com.fg7.client.CustomerServiceClient;
import com.fg7.domain.Customer;
import com.fg7.domain.PurchaseOrder;
import com.fg7.domain.PurchaseOrderWithCustomerInfo;
import com.fg7.repository.PurchaseOrderRepository;
import com.fg7.utils.ContextCacheHolder;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@DefaultProperties(commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "8000")})
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

    public PurchaseOrder saveOrder(PurchaseOrder purchaseOrder) {
        return this.purchaseOrderRepository.save(purchaseOrder);
    }

    public PurchaseOrder getOrderById(Long id) {
        return this.purchaseOrderRepository
                .findById(id)
                .orElseThrow(() -> new PurchaseOrderException("Order: " + id + " is not found"));
    }

    public List<PurchaseOrder> getOrders() {
        return (List<PurchaseOrder>) this.purchaseOrderRepository.findAll();
    }

    @HystrixCommand(
            fallbackMethod = "getDefaultInfo",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy",  value = "THREAD"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "3" ),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "90" ),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")},
            threadPoolKey = "purchaseOrderCustomerInfoPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "5"),
                    @HystrixProperty(name = "maxQueueSize", value = "-1"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000" ),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "10")}
    )
    public PurchaseOrderWithCustomerInfo getOrdersWithCustomerInfo(Long orderId, Long customerId) {
        simulateLongPoll();
        log.info(" *** Retrieving token: {} from shared context", ContextCacheHolder.getCache().getTokenID());
        PurchaseOrderWithCustomerInfo purchaseOrderWithCustomerInfo = getPurchaseOrderWithCustomerInfo(orderId, customerId);
        return purchaseOrderWithCustomerInfo;
    }


    @SuppressWarnings("unused")
    private PurchaseOrderWithCustomerInfo getDefaultInfo(Long orderId, Long customerId) {
        PurchaseOrderWithCustomerInfo fallBackInfo = new PurchaseOrderWithCustomerInfo();
        fallBackInfo.setCustomerFirstName("First name");
        fallBackInfo.setCustomerLastName("Last name");
        fallBackInfo.setOrderedItem("Ordered item");
        return fallBackInfo;
    }

    private PurchaseOrderWithCustomerInfo getPurchaseOrderWithCustomerInfo(Long orderId, Long customerId) {
        Customer customer = this.customerServiceClient.getCustomer(customerId);
        PurchaseOrderWithCustomerInfo purchaseOrderWithCustomerInfo = new PurchaseOrderWithCustomerInfo();
        purchaseOrderWithCustomerInfo.setOrderedItem(this.getOrderById(orderId).getItem());
        purchaseOrderWithCustomerInfo.setCustomerFirstName(customer.getFirstName());
        purchaseOrderWithCustomerInfo.setCustomerLastName(customer.getLastName());
        return purchaseOrderWithCustomerInfo;
    }

    private void simulateLongPoll() {
        Random random = new Random();
        if (random.nextInt(10) > 5) {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }




}
