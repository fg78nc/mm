package com.fg7.client;

import com.fg7.domain.Customer;
import com.fg7.repository.CustomerRedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomerServiceRestTemplateClient {

    // TODO CHANGE TO ZUUL SERVER URL
    private final static String CUSTOMER_SERVICE_URL = "http://localhost:9000/api/customer-service/v1/customers/{id}";

    private final OAuth2RestTemplate oAuth2RestTemplate;
    private final CustomerRedisRepository customerRedisRepository;

    public CustomerServiceRestTemplateClient(OAuth2RestTemplate oAuth2RestTemplate, CustomerRedisRepository redisRepository) {
        this.oAuth2RestTemplate = oAuth2RestTemplate;
        this.customerRedisRepository = redisRepository;
    }


    public Customer getCustomerInfo(Long customerId) {
        Customer customerFromCache = this.getCustomerFromCache(customerId);
        if (customerFromCache != null) {
            log.info("Got customer {} info from cache", customerId);
            return customerFromCache;
        }
        log.info("Did not find customer with ID {} in cache", customerId);

        ResponseEntity<Customer> restExchange =
                oAuth2RestTemplate.exchange(CUSTOMER_SERVICE_URL, HttpMethod.GET, null, Customer.class, customerId);

        Customer customer = restExchange.getBody();
        this.cacheCustomerData(customer);
        return customer;
    }

    private Customer getCustomerFromCache(Long customerId) {
        try {
            return customerRedisRepository.findCustomer(customerId);
        } catch (Exception ex) {
            log.info("Could not retrieve customer info, customer id {}. Got exception {} ", customerId, ex);
        }
        return null;
    }

    private void cacheCustomerData(Customer customer) {
        try {
            this.customerRedisRepository.saveCustomer(customer);
        } catch (Exception ex) {
            log.info("Error {}", ex);
        }
    }


}
