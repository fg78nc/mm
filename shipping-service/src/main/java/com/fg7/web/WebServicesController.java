package com.fg7.web;

import com.fg7.client.CustomerFeignClient;
import com.fg7.client.WebServicesDiscoveryClient;
import com.fg7.domain.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/")
public class WebServicesController {

    private final WebServicesDiscoveryClient webServicesDiscoveryClient;
    private final CustomerFeignClient customerFeignClient;

    public WebServicesController(WebServicesDiscoveryClient webServicesDiscoveryClient,
                                 CustomerFeignClient customerFeignClient) {
        this.webServicesDiscoveryClient = webServicesDiscoveryClient;
        this.customerFeignClient = customerFeignClient;
    }

    @GetMapping("/discovery")
    public List<String> getListOfWebServices() {
        return this.webServicesDiscoveryClient.getListOfAvailableServiceFromEureka();
    }

    @GetMapping("/customer/{customerId}")
    public Customer getCustomerInfo(@PathVariable("customerId") Long customerId) {
        return this.customerFeignClient.getCustomerDeclaratively(customerId);
    }
}
