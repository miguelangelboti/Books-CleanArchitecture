package com.miguelangelboti.books.domain.executor;

import com.miguelangelboti.books.domain.interactor.Interactor;

/**
 * Executor implementation can be based on different frameworks or techniques of asynchronous
 * execution, but every implementation will execute the {@link Interactor} out of the UI thread.
 * <p/>
 * Use this class to execute an {@link Interactor}.
 */
public interface ThreadExecutor {

    /**
     * Executes a {@link Runnable}.
     * @param runnable The class that implements {@link Runnable} interface.
     */
    void execute(final Runnable runnable);
}
