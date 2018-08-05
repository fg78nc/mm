package com.fg7.utils;

public class ContextCache {

    private static final ThreadLocal<String> tokenIdTL = new ThreadLocal<>();

    public static String getTokenId() {
        return tokenIdTL.get();
    }

    public static void setTokenId(String value) {
        tokenIdTL.set(value);
    }

}



