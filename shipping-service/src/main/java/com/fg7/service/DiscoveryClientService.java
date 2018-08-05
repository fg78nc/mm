package com.fg7.service;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscoveryClientService {

    private DiscoveryClient discoveryClient;

    public DiscoveryClientService(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    public List<String> getListOfAvailableServiceFromEureka() {
        return this.discoveryClient.getServices();
    }
}
