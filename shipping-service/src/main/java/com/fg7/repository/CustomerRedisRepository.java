package com.fg7.repository;

import com.fg7.domain.Customer;

public interface CustomerRedisRepository {

    void saveCustomer(Customer customer);
    void updateCustoemr(Customer customer);
    void deleteCustomer(Customer customer);
    Customer findCustomer(Long customerId);
}
