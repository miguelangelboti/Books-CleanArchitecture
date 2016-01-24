package com.miguelangelboti.books.mobile.main.view.fragment.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.miguelangelboti.books.R;
import com.miguelangelboti.books.mobile.main.model.BookViewModel;
import com.miguelangelboti.books.mobile.navigation.Navigator;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Miguel Ángel Botija.
 */
public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.ViewHolder> {

    private final Navigator navigator;

    private List<BookViewModel> books;

    public SearchRecyclerViewAdapter(Navigator navigator, ArrayList<BookViewModel> books) {
        this.navigator = navigator;
        this.books = books;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final BookViewModel book = books.get(position);

        Picasso.with(holder.imageView.getContext()).load(book.getImageUrl()).into(holder.imageView);
        holder.textView.setText(book.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigator.navigateToBooksDetails(holder.itemView.getContext(), book);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (books != null) ? books.size() : 0;
    }

    public void setBooks(List<BookViewModel> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public TextView textView;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imageView);
            textView = (TextView) view.findViewById(R.id.textView);
        }
    }
}
