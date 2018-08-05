package com.fg7.utils.hystrix;

import com.fg7.utils.context.ContextCache;
import com.fg7.utils.context.ContextCacheHolder;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariable;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableLifecycle;
import com.netflix.hystrix.strategy.properties.HystrixProperty;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class EnrichedConcurrencyStrategy extends HystrixConcurrencyStrategy {

    private ContextCache contextCache;
    private HystrixConcurrencyStrategy cHCST;

    public EnrichedConcurrencyStrategy(HystrixConcurrencyStrategy cHCST) {
        super();
        this.cHCST = cHCST;
    }

    @Override
    public ThreadPoolExecutor getThreadPool(HystrixThreadPoolKey threadPoolKey, HystrixProperty<Integer> corePoolSize, HystrixProperty<Integer> maximumPoolSize, HystrixProperty<Integer> keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        return cHCST != null
        ? cHCST.getThreadPool(threadPoolKey, corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue)
        : super.getThreadPool(threadPoolKey, corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    public ThreadPoolExecutor getThreadPool(HystrixThreadPoolKey threadPoolKey, HystrixThreadPoolProperties threadPoolProperties) {
        return cHCST != null
                ? cHCST.getThreadPool(threadPoolKey, threadPoolProperties)
                : super.getThreadPool(threadPoolKey, threadPoolProperties);
    }

    @Override
    public BlockingQueue<Runnable> getBlockingQueue(int maxQueueSize) {
        return cHCST != null
                ? cHCST.getBlockingQueue(maxQueueSize)
                : super.getBlockingQueue(maxQueueSize);
    }

    @Override
    public <T> Callable<T> wrapCallable(Callable<T> hystrixCallable) {
        return cHCST != null
                ? cHCST.wrapCallable(callableDecorator(hystrixCallable))
                : super.wrapCallable(callableDecorator(hystrixCallable));
    }

    @Override
    public <T> HystrixRequestVariable<T> getRequestVariable(HystrixRequestVariableLifecycle<T> rv) {
        return cHCST != null
                ? cHCST.getRequestVariable(rv)
                : super.getRequestVariable(rv);
    }

    private <T> Callable<T> callableDecorator(Callable<T> hystrixCallable) {
        contextCache = ContextCacheHolder.getCache();
        return () -> { // we are in
            ContextCacheHolder.setCache(contextCache);
            try {
                return hystrixCallable.call();
            } finally {
                 contextCache = null;
            }
        };
    }
}
