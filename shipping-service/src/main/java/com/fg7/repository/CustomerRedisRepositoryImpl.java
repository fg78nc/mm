package com.fg7.repository;

import com.fg7.domain.Customer;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRedisRepositoryImpl implements CustomerRedisRepository {

    private static final String HASH = "purchase_order";
    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, Long, Customer> hashOperations;

    public CustomerRedisRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void saveCustomer(Customer customer) {
        hashOperations.put(HASH, customer.getId(), customer);
    }

    @Override
    public void updateCustoemr(Customer customer) {
        hashOperations.put(HASH, customer.getId(), customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        hashOperations.delete(HASH, customer.getId(), customer);
    }

    @Override
    public Customer findCustomer(Long customerId) {
        return hashOperations.get(HASH, customerId);
    }

//    @PostConstruct
//    private void init() {
//        hashOperations = redisTemplate.opsForHash();
//    }
}
