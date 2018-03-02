package com.alnagem.sharkfeed.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alnagem.sharkfeed.R;
import com.alnagem.sharkfeed.model.FlickrPhoto;
import com.alnagem.sharkfeed.ui.OnSearchItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lalnagem on 2/28/18.
 */

public class AdapterSharkSearch extends RecyclerView.Adapter<AdapterSharkSearch.SharkSearchViewHolder> {
    List<FlickrPhoto> data;
    int itemLayout;
    Context context;
    private OnSearchItemClickListener mListener;


    public AdapterSharkSearch(List<FlickrPhoto> data, int itemLayout, Context context, OnSearchItemClickListener listener) {
        this.data = data;
        this.itemLayout = itemLayout;
        this.context = context;
        this.mListener = listener;

    }

    @Override
    public SharkSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new SharkSearchViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(SharkSearchViewHolder holder, final int position) {
        Picasso.with(context).load(data.get(position).getThumbnailUrl()).into(holder.searchImage);

        holder.searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class SharkSearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.search_image)
        ImageView searchImage;

        public OnSearchItemClickListener mListener;

        public SharkSearchViewHolder(View v, OnSearchItemClickListener listener) {
            super(v);
            ButterKnife.bind(this, v);

            mListener = listener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(v, getAdapterPosition());
        }
    }

    public void setData(List<FlickrPhoto> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public FlickrPhoto getData(int position) {
        return data.get(position);
    }
}
