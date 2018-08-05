package com.fg7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean // used by Spring Security to handle authentication
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    @Bean // UserDetailsService is used by Spring Security to handle user information that will be returned to the Spring Security
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("fg").password("{noop}pass").roles("USER")
                .and()
                .withUser("gg").password("{noop}pass").roles("ADMIN");
    }



    // @Bean
    // public TokenStore tokenStore() {
    // return new JdbcTokenStore(dataSource());
    // }

    // @Override
    // public void configure(ClientDetailsServiceConfigurer clients) throws
    // Exception {
    // clients.jdbc(dataSource());
    // }
    //
    // @Bean
    // public DataSource dataSource() {
    // DriverManagerDataSource dataSource = new DriverManagerDataSource();
    // dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
    // dataSource.setUrl("jdbc:hsqldb:hsql://localhost:testdb");
    // dataSource.setUsername("SA");
    // dataSource.setPassword("");
    // return dataSource;
    // }

}
