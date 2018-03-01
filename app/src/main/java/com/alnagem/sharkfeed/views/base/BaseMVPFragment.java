package com.alnagem.sharkfeed.views.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by lalnagem on 2/28/18.
 */

public abstract class BaseMVPFragment<V, P extends BaseMVPPresenter<V>> extends Fragment {

    private P mPresenter;

    public BaseMVPFragment() {
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView(getMVPView());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
    }

    @NonNull
    protected abstract P createPresenter();

    @NonNull
    protected abstract V getMVPView();

    public P getPresenter() {
        return mPresenter;
    }
}
