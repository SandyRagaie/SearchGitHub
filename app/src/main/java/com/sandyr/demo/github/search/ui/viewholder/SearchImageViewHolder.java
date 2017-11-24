package com.sandyr.demo.github.search.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sandyr.demo.github.R;
import com.sandyr.demo.github.search.model.Item;
import com.sandyr.demo.github.search.ui.adapters.SearchAdapterListener;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;

public class SearchImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView imageView;
    private TextView titleTextView;
    private TextView idTextView;
    private TextView descriptionTextView;
    private SearchAdapterListener adapterListener;

    public SearchImageViewHolder(View view, SearchAdapterListener listener) {
        super(view);
        adapterListener = listener;
        this.imageView = ButterKnife.findById(view, R.id.gallery_item_image);
        this.titleTextView = ButterKnife.findById(view, R.id.gallery_item_title);
        this.idTextView = ButterKnife.findById(view, R.id.gallery_item_image_id);
        this.descriptionTextView = ButterKnife.findById(view, R.id.gallery_item_description);
        view.setOnClickListener(this);
    }

    public void onBind(Item item) {

        if (item.getOwner().getAvatarUrl() != null) {
            //Download image using picasso library
            Picasso.with(itemView.getContext()).load(item.getOwner().getAvatarUrl())
                    .error(R.drawable.ic_placeholder)
                    .placeholder(R.drawable.ic_placeholder)
                    .into(imageView);
        }
        //Setting text view title
        titleTextView.setText(item.getName());
        //Setting text view Forks
        idTextView.setText(""+item.getForksCount());
        //Setting text View description
        descriptionTextView.setText(item.getDescription());
    }

    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        adapterListener.onImageClick(position);
    }
}