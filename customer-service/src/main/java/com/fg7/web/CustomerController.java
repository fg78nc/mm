package com.fg7.web;

import com.fg7.domain.Customer;
import com.fg7.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/customers")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable long id){
        return this.customerService.findById(id);
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return this.customerService.findAll();
    }


}
