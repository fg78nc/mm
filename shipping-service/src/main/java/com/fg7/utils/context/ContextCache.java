package com.fg7.utils.context;

import org.springframework.stereotype.Component;

@Component
public final class ContextCache {

    private static final ThreadLocal<String> tokenId = new ThreadLocal<>();

    public String getTokenID() {
        return tokenId.get();
    }

    public void setTokenId(String tokenIdValue) {
        tokenId.set(tokenIdValue);
    }

}



