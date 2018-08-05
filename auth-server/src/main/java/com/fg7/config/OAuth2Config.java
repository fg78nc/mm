package com.fg7.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;

    public OAuth2Config(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("shipping")
                .secret("{noop}pass")
                .authorizedGrantTypes("refresh-token","password","client_credentials")
                .scopes("webclient");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager) // use default authentication manager
                .userDetailsService(userDetailsService); // use user details service
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("permitAll()");
    }
}
