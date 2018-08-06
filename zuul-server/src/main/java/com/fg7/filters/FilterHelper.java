package com.fg7.filters;

import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class FilterHelper {

    public static final String TOKEN_ID = "x-mw-id";
    public static final String AUTH_TOKEN = "Authorization";

    String getTokenId(){
        RequestContext requestContext = RequestContext.getCurrentContext();
        String header = requestContext.getRequest().getHeader(TOKEN_ID);
        if (header == null){
            log.info("header not found in request scope, looking in Zuul Request Scope");
            final Map<String, String> zuulRequestHeaders =  RequestContext.getCurrentContext().getZuulRequestHeaders();
            log.info("listing headers in Zull Request Scope");
            zuulRequestHeaders.forEach((k,v) -> log.info(k + ":" + v));
            header = zuulRequestHeaders.get(TOKEN_ID);
            log.info("retrieved header {} from Zuul Request scope", header);
        }
        log.info("returning header {} ", header);
        return header;
    }

    void setTokenId(String tokenId) {
        log.info("setting {} into ZuulRequestHeaders", tokenId);
        RequestContext.getCurrentContext().addZuulRequestHeader(TOKEN_ID, tokenId);
    }

    boolean isTokenIdPresent(){
        return getTokenId() != null;
    }

    String generateTokenId() {
        return java.util.UUID.randomUUID().toString();
    }

}
