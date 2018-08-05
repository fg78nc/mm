package com.fg7.utils;

import org.springframework.stereotype.Component;

@Component
public final class ContextCache {

    private String tokenId = "";

    public String getTokenID() {
        return this.tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
}



