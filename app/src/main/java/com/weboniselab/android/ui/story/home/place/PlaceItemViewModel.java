
package com.weboniselab.android.ui.story.home.place;

import com.weboniselab.android.data.remote.api.Place;
import com.weboniselab.android.data.remote.api.Result;


public class PlaceItemViewModel {

    private Result mBlog;

    public BlogItemViewModelListener mListener;

    public PlaceItemViewModel(Result blog, BlogItemViewModelListener listener) {
        this.mBlog = blog;
        this.mListener = listener;
    }

    public void onItemClick() {
        mListener.onItemClick(mBlog.getFormattedAddress());
    }

    public interface BlogItemViewModelListener {
        void onItemClick(String blogUrl);
    }
}
