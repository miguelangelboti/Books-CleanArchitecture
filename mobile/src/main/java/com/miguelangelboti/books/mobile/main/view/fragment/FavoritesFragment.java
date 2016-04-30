package com.miguelangelboti.books.mobile.main.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.miguelangelboti.books.R;
import com.miguelangelboti.books.mobile.base.view.fragment.BaseFragment;
import com.miguelangelboti.books.mobile.di.components.BooksComponent;
import com.miguelangelboti.books.mobile.main.model.BookViewModel;
import com.miguelangelboti.books.mobile.main.presenter.FavoritesPresenter;
import com.miguelangelboti.books.mobile.main.view.FavoritesView;
import com.miguelangelboti.books.mobile.main.view.fragment.adapters.LinearSpacingItemDecoration;
import com.miguelangelboti.books.mobile.main.view.fragment.adapters.SearchRecyclerViewAdapter;

import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import butterknife.Bind;

public class FavoritesFragment extends BaseFragment implements FavoritesView {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    @Inject
    FavoritesPresenter presenter;

    private SearchRecyclerViewAdapter recyclerViewAdapter;

    /**
     * Instantiates a new favorites fragment.
     */
    public FavoritesFragment() {
        // Required empty public constructor.
    }

    /**
     * Use this factory method to create a new instance of this fragment.
     * @return A new instance of this fragment.
     */
    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        initializeInjector();
    }

    private void initializeInjector() {
        getComponent(BooksComponent.class).inject(this);
        presenter.setView(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_favorites;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        recyclerViewAdapter = new SearchRecyclerViewAdapter(navigator, null);

        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new LinearSpacingItemDecoration(getActivity(), 6));
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.resume();
    }

// region BaseFragment

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

// endregion
// region FavoritesView

    @Override
    public void loadFavorites(@Nonnull List<BookViewModel> books) {
        recyclerViewAdapter.setBooks(books);
    }

    @Override
    public void showError() {
        showSnackMessage("Error!");
    }

// endregion
}
