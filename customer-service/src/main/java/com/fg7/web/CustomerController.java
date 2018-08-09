package com.fg7.web;

import com.fg7.domain.Customer;
import com.fg7.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
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

    @PostMapping
    public ResponseEntity<?> saveCustomer(@Valid @RequestBody Customer customer, BindingResult result) {
        if (result.hasErrors()){
            log.info("Error : {}", result.getTarget());
        }
        final Customer savedCustomer = this.customerService.save(customer);
        log.info("Persisting customer {}", customer);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("v1/customers/{id}")
                .buildAndExpand(savedCustomer.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
