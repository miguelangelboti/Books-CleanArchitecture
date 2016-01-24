package com.miguelangelboti.books.mobile.main.view.fragment.adapters;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

/**
 * @author Miguel Ángel Botija.
 */
public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int spacing;

    /**
     * Instantiates a new grid spacing item decoration.
     * @param context The context.
     * @param spanCount The span count.
     * @param spacing The spacing in pixels.
     */
    public GridSpacingItemDecoration(Context context, int spanCount, int spacing) {
        this.spanCount = spanCount;
        this.spacing = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, spacing, context.getResources().getDisplayMetrics());
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount; // item column

        outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
        outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
        if (position >= spanCount) {
            outRect.top = spacing; // item top
        }
    }
}
