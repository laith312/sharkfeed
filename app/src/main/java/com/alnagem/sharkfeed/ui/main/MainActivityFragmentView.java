package com.alnagem.sharkfeed.ui.main;

import com.alnagem.sharkfeed.model.FlickrPhoto;

import java.util.List;

/**
 * Created by lalnagem on 2/28/18.
 */

public interface MainActivityFragmentView {
    void updateSearchResults(List<FlickrPhoto> searchResults);

    void stopRefresh();
}
