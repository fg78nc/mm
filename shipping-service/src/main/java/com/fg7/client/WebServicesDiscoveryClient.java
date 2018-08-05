package com.fg7.client;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebServicesDiscoveryClient {

    private DiscoveryClient discoveryClient;

    public WebServicesDiscoveryClient(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    public List<String> getListOfAvailableServiceFromEureka() {
        return this.discoveryClient.getServices();
    }
}
