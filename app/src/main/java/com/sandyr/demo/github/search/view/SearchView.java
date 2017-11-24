package com.sandyr.demo.github.search.view;

import com.sandyr.demo.github.search.model.Item;

import java.util.ArrayList;

/**
 * Created by sandyr on 11/20/2017.
 */

public interface SearchView {
    /**
     * Show progress
     */
    void showProgress();

    /**
     * hide progress
     */
    void hideProgress();

    /**
     * called when items received from server
     *
     * @param images
     */
    void onLoadImagesByPhraseSuccess(ArrayList<Item> images);

    /**
     * called when error received from server
     *
     * @param result
     */
    void onLoadImagesByPhraseError(String result);

    /**
     * called when empty result received from server
     */
    void onEmptyResult();

    /**
     * called when ui clear needed
     */
    void onRemoveImagesFromUI();
}
