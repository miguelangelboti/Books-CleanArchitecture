package com.miguelangelboti.books.mobile.base.presenter;

/**
 * Interface definition for a presenter in a model view presenter (MVP) pattern.
 * @author Miguel Ángel Botija.
 */
public interface Presenter {

    /**
     * Method that control the lifecycle of the view. It should be called in the view's (Activity or Fragment)
     * onResume() method.
     */
    void resume();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's (Activity or Fragment) onPause()
     * method.
     */
    void pause();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's (Activity or Fragment)
     * onDestroy() method.
     */
    void destroy();
}
