package com.alnagem.sharkfeed.ui.photo_detail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alnagem.sharkfeed.R;
import com.alnagem.sharkfeed.views.base.BaseMVPFragment;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lalnagem on 2/28/18.
 */

public class PhotoDetailFragment extends BaseMVPFragment<PhotoDetailFragmentView, PhotoDetailFragmentPresenter> implements PhotoDetailFragmentView {

    @BindView(R.id.flickr_image)
    ImageView flickrImage;

    @BindView(R.id.description_text)
    TextView descriptionText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_detail, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        String id = bundle.getString("id");
        getPresenter().setPhotoId(id);
    }

    @NonNull
    @Override
    protected PhotoDetailFragmentPresenter createPresenter() {
        return new PhotoDetailFragmentPresenter();
    }

    @NonNull
    @Override
    protected PhotoDetailFragmentView getMVPView() {
        return this;
    }

    @Override
    public void setDescription(String description) {
        descriptionText.setText(description);
    }

    @Override
    public void setImageUrl(String imageUrl) {
        Picasso.with(getContext()).load(imageUrl).into(flickrImage);
    }
}
