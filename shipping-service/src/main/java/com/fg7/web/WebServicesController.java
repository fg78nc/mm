package com.fg7.web;

import com.fg7.service.DiscoveryClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/")
public class WebServicesController {

    private DiscoveryClientService discoveryClientService;

    public WebServicesController(DiscoveryClientService discoveryClientService) {
        this.discoveryClientService = discoveryClientService;
    }

    @GetMapping("/discovery")
    public List<String> getListOfWebServices() {
        return this.discoveryClientService.getListOfAvailableServiceFromEureka();
    }
}
