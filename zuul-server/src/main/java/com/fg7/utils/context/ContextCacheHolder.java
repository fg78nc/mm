package com.fg7.utils.context;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class ContextCacheHolder {

    private static final ThreadLocal<ContextCache> sharedContextTL = new ThreadLocal<>();

    public static ContextCache getCache() {
        ContextCache contextCache = sharedContextTL.get();
        return contextCache == null ? getEmptyCache() : contextCache;
    }

    public static final void setCache(ContextCache context) {
        sharedContextTL.set(Objects.requireNonNull(context));
    }

    private static final ContextCache getEmptyCache() {
        sharedContextTL.set(new ContextCache());
        return sharedContextTL.get();
    }
}
