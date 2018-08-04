package com.fg7.web;

import com.fg7.config.ConfigProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class PurchaseOrderController {

    private ConfigProperties configProperties;

    public PurchaseOrderController(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

    @GetMapping("/test-config-server")
    public String testConfigProperties() {
        return this.configProperties.getExampleProperty();
    }
}
