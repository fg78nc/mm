package com.fg7.utils.context;

import org.springframework.stereotype.Component;

@Component
public final class ContextCache {

    private static final ThreadLocal<String> tokenId = new ThreadLocal<>();
    private static final ThreadLocal<String> customerId = new ThreadLocal<>();

    public static String getCustomerId() {
        return customerId.get();
    }

    public static void setCustomerId(String customerIdValue) {
        customerId.set(customerIdValue);
    }

    public static String getTokenID() {
        return tokenId.get();
    }

    public static void setTokenId(String tokenIdValue) {
        tokenId.set(tokenIdValue);
    }
}



