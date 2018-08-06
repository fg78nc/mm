package com.fg7.config;

import com.fg7.utils.filter.ContextCacheFilter;
import com.fg7.utils.filter.ContextInterceptor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import javax.servlet.Filter;
import java.util.Collections;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        RestTemplate template = new RestTemplate();
        final List<ClientHttpRequestInterceptor> interceptors = template.getInterceptors();
        if (interceptors.size() == 0 || interceptors == null){
            template.setInterceptors(Collections.singletonList(new ContextInterceptor()));
        } else {
            interceptors.add(new ContextInterceptor());
            template.setInterceptors(interceptors);
        }
        return template;
    }

    @Bean
    public Filter contextCacheFilter() {
        ContextCacheFilter filter = new ContextCacheFilter();
        return filter;
    }


}
