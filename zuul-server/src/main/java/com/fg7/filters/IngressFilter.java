package com.fg7.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class IngressFilter extends ZuulFilter {

    private final FilterHelper filterHelper;

    public IngressFilter(FilterHelper filterHelper) {
        this.filterHelper = filterHelper;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        if (filterHelper.isTokenIdPresent()){
            log.info("token-id is present {}", filterHelper.getTokenId());
        } else {
            filterHelper.setTokenId(filterHelper.generateTokenId());
            log.info("token-id generated {}", filterHelper.getTokenId());
        }
        RequestContext requestContext = RequestContext.getCurrentContext();
        log.info("Intercepting request for {}", requestContext.getRequest().getRequestURI());
        return null;
    }





}
