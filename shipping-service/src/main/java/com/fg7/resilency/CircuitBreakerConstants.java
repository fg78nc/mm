package com.fg7.resilency;

import org.springframework.beans.factory.annotation.Value;

public class CircuitBreakerConstants {


    @Value("${cb.cor-num:15}")
    public static final String NUMBER_OF_CORES = "15";

    @Value("${cb.iso-thr-timeout:10000}")
    public static final String ISOLATION_THREAD_TIMEOUT = "10000";

    @Value("${cb.req-vol-thr:10}")
    public static final String REQUEST_VOLUME_THRESHOLD = "10";

    @Value("${cb.err-thr-per:70}")
    public static final String ERROR_THRESHOLD_PERCENTAGE = "70";

    @Value("${cb.max-que-siz:10}")
    public static final String MAX_QUEUE_SIZE = "10";

    @Value("${cb.rol-sta-tim-mil:14000}")
    public static final String ROLLING_STATS_TIME_MILLS = "14000";

    @Value("${cb.num-buc:10}")
    public static final String ROLLING_STATS_NUMBER_OF_BUCKETS = "10";

    @Value("${cb.sle-win-mil:7000}")
    public static final String SLEEP_WINDOW_MILLS = "7000";

    @Value("${cb.rnd-bnd:10}")
    public static final int RANDOM_BOUNDARY = 10;

    @Value("${cb.sml-slp:10}")
    public static final int SIMULATED_SLEEP_TIME = 10;
}
