package com.fg7.utils.filter;


import com.fg7.utils.context.ContextCacheConstants;
import com.fg7.utils.context.ContextCacheHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

@Slf4j
public class ContextInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        String tokenId = ContextCacheHolder.getCache().getTokenID();
        log.info("Setting token id {}", tokenId);
        request.getHeaders().add(ContextCacheConstants.TOKEN_ID, tokenId);
        return execution.execute(request, body);
    }
}
