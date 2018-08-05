package com.fg7.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BlueGreenFilter extends ZuulFilter {

    private final FilterHelper filterHelper;

    public BlueGreenFilter(FilterHelper filterHelper, RestTemplate restTemplate) {
        this.filterHelper = filterHelper;
    }

    @Override
    public String filterType() {
        return "route";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        return null;
    }
}
