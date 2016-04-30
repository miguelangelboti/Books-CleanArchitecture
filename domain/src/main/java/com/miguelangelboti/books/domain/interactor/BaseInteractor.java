package com.miguelangelboti.books.domain.interactor;

import com.miguelangelboti.books.domain.executor.PostExecutionThread;
import com.miguelangelboti.books.domain.executor.ThreadExecutor;

/**
 * Base interactor that executes a task in background thread and post the result to a callback that will be executed in
 * the UI thread.
 */
public abstract class BaseInteractor implements Interactor {

    private final ThreadExecutor threadExecutor;

    private final PostExecutionThread postExecutionThread;

    public BaseInteractor(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    protected void execute() {
        threadExecutor.execute(this);
    }

    protected void post(Runnable runnable) {
        postExecutionThread.post(runnable);
    }
}
