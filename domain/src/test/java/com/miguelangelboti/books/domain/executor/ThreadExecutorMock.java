package com.miguelangelboti.books.domain.executor;

import javax.annotation.Nonnull;

/**
 * @author Miguel Ángel Botija.
 */
public class ThreadExecutorMock implements ThreadExecutor, PostExecutionThread {

    @Override
    public void execute(@Nonnull Runnable runnable) {
        runnable.run();
    }

    @Override
    public void post(@Nonnull Runnable runnable) {
        runnable.run();
    }
}
