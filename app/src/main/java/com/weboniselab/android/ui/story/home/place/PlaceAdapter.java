

package com.weboniselab.android.ui.story.home.place;

import android.content.Intent;
import android.net.Uri;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.weboniselab.android.data.remote.api.Result;
import com.weboniselab.android.ui.main.BaseViewHolder;
import com.weboniselab.android.utils.app.AppLogger;

import java.util.List;



public class PlaceAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private List<Result> mBlogResponseList;

    private BlogAdapterListener mListener;

    public PlaceAdapter(List<Result> blogResponseList) {
        this.mBlogResponseList = blogResponseList;
    }

    public void setListener(BlogAdapterListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new BlogViewHolder(null);
            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(null);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mBlogResponseList != null && mBlogResponseList.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (mBlogResponseList != null && mBlogResponseList.size() > 0) {
            return mBlogResponseList.size();
        } else {
            return 1;
        }
    }

    public void addItems(List<Result> blogList) {
        mBlogResponseList.addAll(blogList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mBlogResponseList.clear();
    }

    public class BlogViewHolder extends BaseViewHolder implements PlaceItemViewModel.BlogItemViewModelListener {


        private PlaceItemViewModel mPlaceItemViewModel;

        public BlogViewHolder(View view) {
            super(view);
        }

        @Override
        public void onBind(int position) {

            final Result blog = mBlogResponseList.get(position);

            mPlaceItemViewModel = new PlaceItemViewModel(blog, this);


        }

        @Override
        public void onItemClick(String blogUrl) {
            if (blogUrl != null) {
                try {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse(blogUrl));
                    itemView.getContext().startActivity(intent);
                } catch (Exception e) {
                    AppLogger.d("url error");
                }
            }
        }
    }

    public class EmptyViewHolder extends BaseViewHolder implements PlaceEmptyItemViewModel.BlogEmptyItemViewModelListener {


        public EmptyViewHolder(View view) {
            super(view);
        }

        @Override
        public void onBind(int position) {
        }

        @Override
        public void onRetryClick() {
            mListener.onRetryClick();
        }
    }

    public interface BlogAdapterListener {
        void onRetryClick();
    }
}