package com.fg7.utils.filter;


import com.fg7.utils.context.ContextCacheConstants;
import com.fg7.utils.context.ContextCacheHolder;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class ContextInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders()
                .add(ContextCacheConstants.TOKEN_ID, ContextCacheHolder.getCache().getTokenID());
        return execution.execute(request, body);
    }
}
