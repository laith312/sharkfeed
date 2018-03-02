package com.alnagem.sharkfeed.ui.main;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alnagem.sharkfeed.R;
import com.alnagem.sharkfeed.model.FlickrPhoto;
import com.alnagem.sharkfeed.ui.GridSpacingItemDecoration;
import com.alnagem.sharkfeed.ui.OnSearchItemClickListener;
import com.alnagem.sharkfeed.ui.photo_detail.PhotoDetailFragment;
import com.alnagem.sharkfeed.views.base.BaseMVPFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityFragment extends BaseMVPFragment<MainActivityFragmentView, MainActivityFragmentPresenter> implements MainActivityFragmentView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;

    private AdapterSharkSearch searchAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        OnSearchItemClickListener listener = new OnSearchItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                PhotoDetailFragment newFragment = new PhotoDetailFragment();

                Bundle bundle = new Bundle();
                bundle.putSerializable("photo", searchAdapter.getData(position));
                newFragment.setArguments(bundle);
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.add(R.id.fragment_container, newFragment, searchAdapter.getData(position).getId())
                        .addToBackStack(searchAdapter.getData(position).getId()).commit();
            }
        };

        searchAdapter = new AdapterSharkSearch(new ArrayList<FlickrPhoto>(), R.layout.item_shark_search, getContext(), listener);
        recyclerView.setAdapter(searchAdapter);

        RecyclerView.LayoutManager lm = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(lm);

        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, 24, true));
        swipeRefreshLayout.setOnRefreshListener(this);

        return view;
    }

    @NonNull
    @Override
    protected MainActivityFragmentPresenter createPresenter() {
        return new MainActivityFragmentPresenter();
    }

    @NonNull
    @Override
    protected MainActivityFragmentView getMVPView() {
        return this;
    }

    @Override
    public void updateSearchResults(List<FlickrPhoto> searchResults) {
        searchAdapter.setData(searchResults);
    }

    @Override
    public void stopRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        getPresenter().fetchSharksFromFlickr();
        swipeRefreshLayout.setRefreshing(true);
    }
}
