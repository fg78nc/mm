package com.fg7.client;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebServicesDiscoveryClient {

    private final DiscoveryClient discoveryClient;
    private final EurekaClient eurekaClient;

    public WebServicesDiscoveryClient(DiscoveryClient discoveryClient, EurekaClient eurekaClient) {
        this.discoveryClient = discoveryClient;
        this.eurekaClient = eurekaClient;
    }

    public List<String> getListOfAvailableServiceFromEureka() {
        return this.discoveryClient.getServices();
    }

    public String getServiceIPaddress(String serviceName){
        InstanceInfo instanceInfo = this.eurekaClient.getNextServerFromEureka(serviceName, false);
        return instanceInfo.getIPAddr();
    }

}
