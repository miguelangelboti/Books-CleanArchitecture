package com.miguelangelboti.books.domain.interactor;

/**
 * Common interface for an Interactor {@link Runnable} declared in the application. This interface represents a
 * execution unit for different use cases (this means any use case in the application should implement this contract).
 * <p/>
 * By convention each Interactor implementation will return the result using a Callback that should be executed in the
 * UI thread.
 */
public interface Interactor extends Runnable {

    /**
     * Everything inside this method will be executed asynchronously.
     */
    void run();
}
