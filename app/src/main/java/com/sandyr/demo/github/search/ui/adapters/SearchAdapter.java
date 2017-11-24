package com.sandyr.demo.github.search.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sandyr.demo.github.R;
import com.sandyr.demo.github.search.model.Item;
import com.sandyr.demo.github.search.ui.viewholder.SearchImageViewHolder;

import java.util.ArrayList;
import java.util.List;


public class SearchAdapter extends RecyclerView.Adapter<SearchImageViewHolder> {
    private final ArrayList<Item> imagesList = new ArrayList<>();
    private final SearchAdapterListener adapterListener;

    public SearchAdapter(SearchAdapterListener listener) {
        this.adapterListener = listener;
    }

    @Override
    public SearchImageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_repo_adapter_cell, null);
        return new SearchImageViewHolder(view, adapterListener);
    }

    @Override
    public void onBindViewHolder(SearchImageViewHolder customViewHolder, int position) {
        customViewHolder.onBind(imagesList.get(position));
    }


    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    @Override
    public void onViewAttachedToWindow(final SearchImageViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (isAlmostLastItem(holder)) {
            adapterListener.loadNextPageOnEndOfListReached();
        }
    }

    public void insertImages(List<Item> images) {
        int startPosition = imagesList.size();
        int endPosition = startPosition + images.size() - 1;
        imagesList.addAll(images);
        notifyItemRangeInserted(startPosition, endPosition);
    }

    public void removeAllImages() {
        imagesList.clear();
        notifyDataSetChanged();
    }

    public ArrayList<Item> getCachedImages() {
        return imagesList;
    }

    private boolean isAlmostLastItem(SearchImageViewHolder holder) {
        int adapterPosition = holder.getAdapterPosition();
        if(adapterPosition < 3){
            return false;
        }
        return holder.getAdapterPosition() == (imagesList.size() - 3);
    }

}

