package com.sandyr.demo.github.search.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.sandyr.demo.github.R;
import com.sandyr.demo.github.search.model.Item;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class GitHubRepoDetailsActivity extends AppCompatActivity {

    private static final String MAIN_ACTIVITY_ITEMS = "#ITEMS";
    @BindView(R.id.image_details)
    ImageView imageView;
    @BindView(R.id.title_details)
    TextView title_textview;
    @BindView(R.id.caption_details)
    TextView caption_textview;

    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_repo_details);
        unbinder = ButterKnife.bind(this);
        Item item = getIntentExtras();
        Picasso.with(this).load(item.getOwner().getAvatarUrl())
                .error(R.drawable.ic_placeholder)
                .placeholder(R.drawable.ic_placeholder)
                .into(imageView);

        //Setting text view title
        title_textview.setText(item.getName());
        //Setting text view id
        caption_textview.setText(item.getDescription());
    }

    public static Intent newIntent(Context context, Item item) {
        Intent intent = new Intent(context, GitHubRepoDetailsActivity.class);
        intent.putExtra(MAIN_ACTIVITY_ITEMS, item);
        return intent;
    }

    private Item getIntentExtras() {
        Item data = getIntent().getExtras().getParcelable(MAIN_ACTIVITY_ITEMS);
        return data;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
