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
        final String webReaderLink = book.getWebReaderLink();
        List<String> authors = book.getAuthors();
        String firstAuthor = ((authors != null) && (authors.size() > 0)) ? authors.get(0) : null;

        Picasso.with(holder.imageView.getContext()).load(book.getImageUrl()).into(holder.imageView);
        holder.textView01.setText(book.getTitle());
        holder.textView02.setText(firstAuthor);
        if (webReaderLink != null) {
            holder.textView03.setVisibility(View.VISIBLE);
            holder.textView03.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    navigator.openWeb(view.getContext(), webReaderLink);
                }
            });
        } else {
            holder.textView03.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigator.navigateToBooksDetails(view.getContext(), book, holder.imageView, holder.textView01, holder.textView02);
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

        public TextView textView01;

        public TextView textView02;

        public TextView textView03;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imageView);
            textView01 = (TextView) view.findViewById(R.id.textView01);
            textView02 = (TextView) view.findViewById(R.id.textView02);
            textView03 = (TextView) view.findViewById(R.id.textView03);
        }
    }
}
