package com.fg7.client;

import com.fg7.domain.Customer;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomerServiceRestTemplateClient {

    private final static String CUSTOMER_SERVICE_URL = "http://localhost:9000/api/customer-service/v1/customers/{id}";
    private OAuth2RestTemplate oAuth2RestTemplate;

    public CustomerServiceRestTemplateClient(OAuth2RestTemplate oAuth2RestTemplate) {
        this.oAuth2RestTemplate = oAuth2RestTemplate;
    }

    public Customer getCustomerInfo(Long customerId){
        ResponseEntity<Customer> restExchange =
                oAuth2RestTemplate.exchange( CUSTOMER_SERVICE_URL, HttpMethod.GET, null, Customer.class, customerId);

        return restExchange.getBody();
    }
}
