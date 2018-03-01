package com.alnagem.sharkfeed.views.base;

import android.support.annotation.Nullable;

/**
 * Created by lalnagem on 2/28/18.
 */

public abstract class BaseMVPPresenter<V> {

    private V mvpView;

    public BaseMVPPresenter() {
    }

    public final void attachView(V mvpView) {
        this.mvpView = mvpView;
        onMvpViewAttached();
    }

    protected void onMvpViewAttached() {
    }

    public final void detachView() {
        onMvpViewDetached();
        this.mvpView = null;
    }

    protected void onMvpViewDetached() {
    }

    @Nullable
    protected V getMvpView() {
        return this.mvpView;
    }

}
