package com.fg7.config;

import com.fg7.utils.filter.ContextInterceptor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        final List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (interceptors.size() == 0 || interceptors == null) {
            restTemplate.setInterceptors(Collections.singletonList(new ContextInterceptor()));
        } else {
            interceptors.add(new ContextInterceptor());
            restTemplate.setInterceptors(interceptors);
        }
        return restTemplate;
    }

    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate(OAuth2ClientContext oAuth2ClientContext,
                                                 OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails){
        return new OAuth2RestTemplate(oAuth2ProtectedResourceDetails, oAuth2ClientContext);
    }

}
