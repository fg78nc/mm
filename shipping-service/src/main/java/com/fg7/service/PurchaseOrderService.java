package com.fg7.service;

import com.fg7.client.CustomerServiceClient;
import com.fg7.domain.Customer;
import com.fg7.domain.PurchaseOrder;
import com.fg7.domain.PurchaseOrderWithCustomerInfo;
import com.fg7.repository.PurchaseOrderRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.fg7.resilency.CircuitBreakerConstants.*;

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

    public PurchaseOrder saveOrder(PurchaseOrder purchaseOrder) {
        return this.purchaseOrderRepository.save(purchaseOrder);
    }

    public PurchaseOrder getOrderById(Long id) {
        return this.purchaseOrderRepository
                .findById(id)
                .orElseThrow(() -> new PurchaseOrderException("Order does not exist"));
    }

    public List<PurchaseOrder> getOrders() {
        return (List<PurchaseOrder>) this.purchaseOrderRepository.findAll();
    }

    @HystrixCommand(
            fallbackMethod = "getDefaultInfo",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = ISOLATION_THREAD_TIMEOUT),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = REQUEST_VOLUME_THRESHOLD),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = ERROR_THRESHOLD_PERCENTAGE)},
            threadPoolKey = "purchaseOrderCustomerInfoPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = NUMBER_OF_CORES),
                    @HystrixProperty(name = "maxQueueSize", value = MAX_QUEUE_SIZE),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value=ROLLING_STATS_TIME_MILLS),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = ROLLING_STATS_NUMBER_OF_BUCKETS),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = SLEEP_WINDOW_MILLS)}
    )
    public PurchaseOrderWithCustomerInfo getOrdersWithCustomerInfo(Long orderId, Long customerId) {
        simulateLongPoll();
        Customer customer = this.customerServiceClient.getCustomer(customerId);
        PurchaseOrderWithCustomerInfo purchaseOrderWithCustomerInfo = new PurchaseOrderWithCustomerInfo();
        purchaseOrderWithCustomerInfo.setOrderedItem(this.getOrderById(orderId).getItem());
        purchaseOrderWithCustomerInfo.setCustomerFirstName(customer.getFirstName());
        purchaseOrderWithCustomerInfo.setCustomerLastName(customer.getLastName());
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

    private void simulateLongPoll() {
        Random random = new Random();
        if (random.nextInt(RANDOM_BOUNDARY) > 5) {
            try {
                TimeUnit.SECONDS.sleep(SIMULATED_SLEEP_TIME);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

}
