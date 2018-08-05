package com.fg7.utils;

import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;

@Slf4j
public class ContextCacheHolder {

    private static final ThreadLocal<ContextCache> sharedContextTL = new ThreadLocal<>();

    public static ContextCache getTLContext(){
        ContextCache contextCache = sharedContextTL.get();
        return contextCache == null ? getEmptySharedContext() : contextCache;
    }

    public static void setTLContext(@NotNull ContextCache context) {
        log.info("set {} into context cache ", context);
        sharedContextTL.set(context);
    }

    private static ContextCache getEmptySharedContext(){
        log.info("returning devoid context cache");
        ContextCache contextCache = new ContextCache();
        sharedContextTL.set(contextCache);
        return sharedContextTL.get();
    }
}
