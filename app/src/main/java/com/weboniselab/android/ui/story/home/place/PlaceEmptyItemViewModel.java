
package com.weboniselab.android.ui.story.home.place;


public class PlaceEmptyItemViewModel {

    private BlogEmptyItemViewModelListener mListener;

    public PlaceEmptyItemViewModel(BlogEmptyItemViewModelListener listener) {
        this.mListener = listener;
    }

    public void onRetryClick() {
        mListener.onRetryClick();
    }

    public interface BlogEmptyItemViewModelListener {
        void onRetryClick();
    }

}
