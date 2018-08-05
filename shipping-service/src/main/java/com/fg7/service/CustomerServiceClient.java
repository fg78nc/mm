package com.fg7.service;

import com.fg7.domain.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class CustomerServiceClient {

    private static final String CUSTOMER_SERVICE_URI = "http://customer-service/v1/customers/{customerId}";

    private RestTemplate restTemplate;

    public CustomerServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Customer getCustomer(long customerId){
        Customer customer = this.restTemplate
                .exchange(CUSTOMER_SERVICE_URI,
                        HttpMethod.GET,
                        null ,
                        Customer.class,
                        customerId)
                .getBody();
        log.info("Retrieved customer {}", customer);
        return customer;
    }
}
