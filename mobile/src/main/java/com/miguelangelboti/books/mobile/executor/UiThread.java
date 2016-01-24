package com.miguelangelboti.books.mobile.executor;

import android.os.Handler;
import android.os.Looper;

import com.miguelangelboti.books.domain.executor.PostExecutionThread;

/**
 * MainThread (UI Thread) implementation based on a Handler instantiated with the main application
 * Looper.
 */
public class UiThread implements PostExecutionThread {

    private final Handler handler;

    private static class LazyHolder {

        private static final UiThread INSTANCE = new UiThread();
    }

    private UiThread() {
        this.handler = new Handler(Looper.getMainLooper());
    }

    public static UiThread getInstance() {
        return LazyHolder.INSTANCE;
    }

    /**
     * Causes the Runnable r to be added to the message queue. The runnable will be run on the main
     * thread.
     * @param runnable {@link Runnable} to be executed.
     */
    @Override
    public void post(Runnable runnable) {
        handler.post(runnable);
    }
}
