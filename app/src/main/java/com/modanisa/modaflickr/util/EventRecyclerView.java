package com.modanisa.modaflickr.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Hasan ACAR on 21.04.2015.
 */
public class EventRecyclerView extends RecyclerView {

    private OnDetectScrollListener onDetectScrollListener;

    public EventRecyclerView(Context context) {
        super(context);
        setListeners();
    }

    public EventRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setListeners();
    }

    public EventRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setListeners();
    }


    private void setListeners() {
        super.addOnScrollListener(new OnScrollListener() {

            private int oldTop;
            private int oldFirstVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView view, int scrollState) {

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(onDetectScrollListener != null)
                    onDetectedListScroll(recyclerView, dx);
            }

            private void onDetectedListScroll(RecyclerView recyclerView, int firstVisibleItem) {
                View view = recyclerView.getChildAt(0);
                int top = (view == null) ? 0 : view.getTop();

                if (firstVisibleItem == oldFirstVisibleItem) {
                    if (top > oldTop) {
                        onDetectScrollListener.onUpScrolling();
                    } else if (top < oldTop) {
                        onDetectScrollListener.onDownScrolling();
                    }
                } else {
                    if (firstVisibleItem < oldFirstVisibleItem) {
                        onDetectScrollListener.onUpScrolling();
                    } else {
                        onDetectScrollListener.onDownScrolling();
                    }
                }

                oldTop = top;
                oldFirstVisibleItem = firstVisibleItem;
            }
        });
    }


    public void setOnDetectScrollListener(OnDetectScrollListener onDetectScrollListener) {
        this.onDetectScrollListener = onDetectScrollListener;
    }

}
