package com.zf.hotupdate_tinker.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zf.hotupdate_tinker.R;
import com.zf.hotupdate_tinker.adpter.PhotoAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoFragment extends BaseFragment {
    private RecyclerView recyclerView;

    public PhotoFragment() {
        // Required empty public constructor
    }

    public static PhotoFragment newInstance() {
        Bundle args = new Bundle();
        PhotoFragment fragment = new PhotoFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected View initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_photo, null);
        recyclerView = view.findViewById(R.id.recyclerview_photo);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        PhotoAdapter photoAdapter = new PhotoAdapter();
        if (photoAdapter != null){
            photoAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        }
        //每一行显示两个
        GridLayoutManager gridLayoutManager  = new GridLayoutManager(context,3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(photoAdapter);
    }
}
