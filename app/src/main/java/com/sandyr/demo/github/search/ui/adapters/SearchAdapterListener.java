package com.sandyr.demo.github.search.ui.adapters;

import android.graphics.drawable.Drawable;

/**
 * Created by sandyr on 11/20/2017.
 */
public interface SearchAdapterListener {

    void onImageClick(int position);

    void loadNextPageOnEndOfListReached();
}
