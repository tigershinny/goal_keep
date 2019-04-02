package com.adidas.sports.goal.widgets;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by liujing on 2017/8/10.
 */
public class RecyclerItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public RecyclerItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(
            Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildItemId(view) != 0) {
            int position = parent.getChildLayoutPosition(view);
            int childCount = parent.getAdapter().getItemCount();
            if (position == childCount - 1) {
                outRect.top = space;
                outRect.bottom = space;
                outRect.left = space;
                outRect.right = space;
            } else {
                outRect.left = space;
                outRect.top = space;
                outRect.right = space;
            }
        }
    }
}
