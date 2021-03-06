package com.fg7.service;

import com.fg7.domain.Customer;
import com.fg7.events.event.CustomerChangedSourceBean;
import com.fg7.repository.CustomerRepository;
import com.fg7.utils.context.ContextCacheHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerChangedSourceBean customerChangedSourceBean;

    public CustomerService(CustomerRepository customerRepository, CustomerChangedSourceBean customerChangedSourceBean) {
        this.customerRepository = customerRepository;
        this.customerChangedSourceBean = customerChangedSourceBean;
    }

//    @HystrixCommand(threadPoolKey = "customer-service-pool")
    public Customer findById(long id) {
        log.info("retrieved token id {}", ContextCacheHolder.getCache().getTokenID());
        return this.customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException("Customer with id :" + id + " not found"));
    }

    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    public Customer save(Customer customer) {
        Customer savedCustomer = this.customerRepository.saveAndFlush(customer);
        this.customerChangedSourceBean.publishCustomerChangedEvent("PERSIST", savedCustomer.getId());
        return savedCustomer;
    }
}
