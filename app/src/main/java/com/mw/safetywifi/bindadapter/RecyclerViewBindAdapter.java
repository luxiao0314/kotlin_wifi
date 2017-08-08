package com.mw.safetywifi.bindadapter;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

/**
 * Created by kelin on 16-4-26.
 */
public class RecyclerViewBindAdapter {

    @BindingAdapter("setAdapter")
    public static void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);    //高度不变,提高性能
    }

    @BindingAdapter({"addItemDecoration"})
    public static void addItemDecoration(RecyclerView view, RecyclerView.ItemDecoration itemDecoration) {
        if (itemDecoration != null)
            view.addItemDecoration(itemDecoration);
    }

    @BindingAdapter({"addOnItemClick"})
    public static void addOnItemClick(RecyclerView view, RecyclerViewItemClickSupport.OnItemClickListener listener) {
        RecyclerViewItemClickSupport.addTo(view).setOnItemClickListener(listener);
    }

    @BindingAdapter({"onScrollListener"})
    public static void setOnScrollListener(RecyclerView view, RecyclerView.OnScrollListener listener) {
        if (listener != null) {
            view.setOnScrollListener(listener);
        }
    }
}
