package com.miguelangelboti.books.data.repository.datasources;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BooksLocalDataSource implements BooksDataSource {

    @Inject
    public BooksLocalDataSource() {
    }

    @Override
    public void getBooks(Callback callback, String query) {
        // TODO: Nothing happens here...
    }
}
