package com.miguelangelboti.books.data.executor;

import com.miguelangelboti.books.domain.executor.ThreadExecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Decorated {@link ThreadPoolExecutor} Singleton class based on 'Initialization on Demand Holder'
 * pattern.
 */
public class JobExecutor implements ThreadExecutor {

    private static final int INITIAL_POOL_SIZE = 3;

    private static final int MAX_POOL_SIZE = 5;

    // Sets the amount of time an idle thread waits before terminating.
    private static final int KEEP_ALIVE_TIME = 10;

    // Sets the Time Unit to seconds.
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

    private final ThreadPoolExecutor threadPoolExecutor;

    private static class LazyHolder {

        private static final JobExecutor INSTANCE = new JobExecutor();
    }

    private JobExecutor() {

        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
        threadPoolExecutor = new ThreadPoolExecutor(
                INITIAL_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                KEEP_ALIVE_TIME_UNIT,
                workQueue);
    }

    public static JobExecutor getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public void execute(Runnable runnable) {
        if (runnable == null) {
            throw new IllegalArgumentException("Runnable to execute cannot be null");
        }
        threadPoolExecutor.execute(runnable);
    }
}
