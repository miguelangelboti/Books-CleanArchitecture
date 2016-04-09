package com.miguelangelboti.books.mobile.main.view.fragment;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.miguelangelboti.books.R;
import com.miguelangelboti.books.mobile.base.view.fragment.BaseFragment;
import com.miguelangelboti.books.mobile.di.components.SearchComponent;
import com.miguelangelboti.books.mobile.main.model.BookViewModel;
import com.miguelangelboti.books.mobile.main.presenter.SearchPresenter;
import com.miguelangelboti.books.mobile.main.view.fragment.adapters.LinearSpacingItemDecoration;
import com.miguelangelboti.books.mobile.main.view.fragment.adapters.SearchRecyclerViewAdapter;

import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import butterknife.Bind;

public class SearchFragment extends BaseFragment implements com.miguelangelboti.books.mobile.main.view.SearchView {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    @Inject
    SearchPresenter searchPresenter;

    private SearchRecyclerViewAdapter recyclerViewAdapter;

    /**
     * Instantiates a new books search fragment.
     */
    public SearchFragment() {
        // Required empty public constructor.
    }

    /**
     * Use this factory method to create a new instance of this fragment.
     * @return A new instance of this fragment.
     */
    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        initializeInjector();
    }

    private void initializeInjector() {
        getComponent(SearchComponent.class).inject(this);
        searchPresenter.setView(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        // Inflate the options menu from XML.
        inflater.inflate(R.menu.menu_search, menu);

        // Get the SearchView and set the searchable configuration.
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                onSearchRequested(query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        // Add the expand/collapse animation to the SearchView.
        LinearLayout searchBar = (LinearLayout) searchView.findViewById(R.id.search_bar);
        if (searchBar != null) {
            searchBar.setLayoutTransition(new LayoutTransition());
        }

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onResume() {
        super.onResume();
        searchPresenter.resume();
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
    // region LoginView

    @Override
    public void loadBooks(@Nonnull List<BookViewModel> books) {
        recyclerViewAdapter.setBooks(books);
    }

    @Override
    public void showError() {
        showSnackMessage("Error!");
    }

    // endregion
    // region Events

    private void onSearchRequested(String query) {
        searchPresenter.doSearch(query);
    }

    // endregion
}
