package com.fg7.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigProperties {

    @Value("${example.property:'not-set'}")
    private String exampleProperty;

    @Value("${secret.property:'not-set'}")
    private String secretProperty;

    public String getExampleProperty() {
        return this.exampleProperty;
    }

    public String getSecretProperty() {
        return this.secretProperty;
    }



}
