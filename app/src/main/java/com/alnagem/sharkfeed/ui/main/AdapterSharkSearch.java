package com.alnagem.sharkfeed.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alnagem.sharkfeed.R;
import com.alnagem.sharkfeed.model.FlickrPhoto;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lalnagem on 2/28/18.
 */

public class AdapterSharkSearch extends RecyclerView.Adapter<AdapterSharkSearch.SharkSearchViewHolder> {
    List<FlickrPhoto> items;
    int itemLayout;
    Context context;


    public AdapterSharkSearch(List<FlickrPhoto> items, int itemLayout, Context context) {
        this.items = items;
        this.itemLayout = itemLayout;
        this.context = context;
    }

    @Override
    public SharkSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);

        return new SharkSearchViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SharkSearchViewHolder holder, int position) {
        Picasso.with(context).load(items.get(position).getThumbnailUrl()).into(holder.searchImage);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class SharkSearchViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.search_image)
        ImageView searchImage;

        public SharkSearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setData(List<FlickrPhoto> data) {
        items = data;
        notifyDataSetChanged();
    }
}
