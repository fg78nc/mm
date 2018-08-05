package com.fg7.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@Component
class ContextCacheFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("context cache filter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = HttpServletRequest.class.cast(request);
        String tokenId = httpServletRequest.getHeader(ContextCacheConstants.TOKEN_ID);
        ContextCacheHolder.getCache().setTokenId(tokenId);
        log.info("Saved token_id {} into context cache", tokenId);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}