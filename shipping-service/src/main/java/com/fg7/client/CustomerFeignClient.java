package com.fg7.client;

import com.fg7.domain.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("customer-service")
public interface CustomerFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/v1/customers/{customerId}", consumes = "application/json")
    Customer getCustomerDeclaratively(@PathVariable("customerId") long id);
}
