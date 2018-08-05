package com.fg7.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class RibbonConfig {

    @Autowired
    public IClientConfig ribbonClientConfig;

    @Bean
    public IPing ping(IClientConfig config){
        return new PingUrl();
    }

    // @RibbonClient(name="service-name", configuration=RibbonConfig.class)
    // service.ribbon.eureka.enable=false
    // service.ribbon.ServiceRefreshInterval=5000
    // service.ribbon.listOfServers=localhost:8801,localhost:8803,localhost:8805
    @Bean
    public IRule rule(IClientConfig config) {
        return new AvailabilityFilteringRule();
    }
}
