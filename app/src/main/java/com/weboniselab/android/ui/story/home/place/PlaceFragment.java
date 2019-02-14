

package com.weboniselab.android.ui.story.home.place;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.View;

import com.weboniselab.android.data.remote.api.Place;
import com.weboniselab.android.data.remote.api.Result;
import com.weboniselab.android.ui.main.BaseFragment;

import java.util.List;

import javax.inject.Inject;



public class PlaceFragment extends BaseFragment<PlaceViewModel> implements PlaceNavigator, PlaceAdapter.BlogAdapterListener {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    PlaceAdapter mPlaceAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;
    
    private PlaceViewModel mPlaceViewModel;

    public static PlaceFragment newInstance() {
        Bundle args = new Bundle();
        PlaceFragment fragment = new PlaceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPlaceViewModel.setNavigator(this);
        mPlaceAdapter.setListener(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUp();
        subscribeToLiveData();
    }

    @Override
    public PlaceViewModel getViewModel() {
        mPlaceViewModel = ViewModelProviders.of(this, mViewModelFactory).get(PlaceViewModel.class);
        return mPlaceViewModel;
    }

    
    private void setUp() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
      /*  mFragmentBlogBinding.blogRecyclerView.setLayoutManager(mLayoutManager);
        mFragmentBlogBinding.blogRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mFragmentBlogBinding.blogRecyclerView.setAdapter(mPlaceAdapter);*/
    }

    private void subscribeToLiveData() {
        mPlaceViewModel.getPlaceLiveData().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(@Nullable List<Result> blogs) {
                mPlaceViewModel.addBlogItemsToList(blogs);
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void updatePlace(Place place) {
        mPlaceAdapter.clearItems();
        mPlaceAdapter.addItems(place.getResults());
    }

    @Override
    public void handleError(Throwable throwable) {
        // handle error
    }

    @Override
    public void onRetryClick() {
        mPlaceViewModel.fetchBlogs();
    }



}
