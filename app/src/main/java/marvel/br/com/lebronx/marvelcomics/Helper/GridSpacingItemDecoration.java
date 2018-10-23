package marvel.br.com.lebronx.marvelcomics.Helper;

import android.graphics.Rect;
import android.support.v4.text.TextUtilsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Locale;

class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int spacing;
    private boolean includeEdge;
    private int headerNum;
    private boolean isRtl = TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == ViewCompat.LAYOUT_DIRECTION_RTL;

    public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge, int headerNum) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
        this.headerNum = headerNum;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view) - headerNum; // item position
        if (position >= 0) {
            int column = position % spanCount; // item column
            if (isRtl) {
                column = spanCount - 1 - column;
            }
            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount;
                outRect.right = (column + 1) * spacing / spanCount;

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount;
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        } else {
            outRect.left = spacing;
            outRect.right = spacing;
            if (includeEdge)
                outRect.top = spacing;
            else
                outRect.top = 0;
            outRect.bottom = 0;
        }
    }

}
