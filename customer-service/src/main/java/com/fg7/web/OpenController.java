package com.fg7.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2")
public class OpenController {

    @GetMapping
    public String openResource() {
        return "OK";
    }
}
