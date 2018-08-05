package com.fg7.service;

import com.fg7.domain.Customer;
import com.fg7.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findById(long id) {
        return this.customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException("Customer with id :" + id + " not found"));
    }

    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }
}
