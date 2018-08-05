package com.fg7.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EngressFilter extends ZuulFilter {

    private final FilterHelper filterHelper;

    public EngressFilter(FilterHelper filterHelper) {
        this.filterHelper = filterHelper;
    }

    @Override
    public String filterType() {
        return "post";
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
        final RequestContext currentContext = RequestContext.getCurrentContext();
        log.info("intercepting and adding token-id: {}", filterHelper.getTokenId());
        currentContext.getResponse().addHeader(FilterHelper.TOKEN_ID, filterHelper.getTokenId());
        log.info("filter processed response {}", currentContext.getRequest().getRequestURI());
        return null;
    }
}
