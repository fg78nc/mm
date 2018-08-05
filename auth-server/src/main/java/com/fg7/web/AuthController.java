package com.fg7.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/auth")
public class AuthController {

    @GetMapping(path = "/userinfo", produces = "application/json")
    public Map<String, Object> user (OAuth2Authentication user) {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("user", user.getUserAuthentication().getPrincipal());
        userInfo.put("authorities", user.getUserAuthentication().getAuthorities());
        return userInfo;
    }

    @GetMapping("/resource")
    @PreAuthorize("hasRole('ADMIN')")
    public String protectedResource() {
        return "Protected resource";
    }

}
