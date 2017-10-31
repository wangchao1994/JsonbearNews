package com.zf.hotupdate_tinker.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zf.hotupdate_tinker.R;
import com.zf.hotupdate_tinker.adpter.PhotoAdapter;
import com.zf.hotupdate_tinker.data.BaseJson;
import com.zf.hotupdate_tinker.data.VideoDataBean;
import com.zf.hotupdate_tinker.data.VideoTabBean;
import com.zf.hotupdate_tinker.net.Api;
import com.zf.hotupdate_tinker.net.QClient;
import com.zf.hotupdate_tinker.net.QService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private PhotoAdapter photoAdapter;
    private List<VideoDataBean.DataBean> data;
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
        swipeRefreshLayout = view.findViewById(R.id.srl_photo);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();

        if (photoAdapter == null) {
            photoAdapter = new PhotoAdapter();
            photoAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
            photoAdapter.setEnableLoadMore(true);
        }
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.RED);
        swipeRefreshLayout.setOnRefreshListener(this);
        //每一行显示两个
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(photoAdapter);
        updateData();
    }

    private void updateData() {
        swipeRefreshLayout.setRefreshing(true);
        QClient.getInstance().create(QService.class, Api.APP_DOMAIN)
                .getPhotoURLData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseJson<VideoDataBean>>() {
                    @Override
                    public void accept(BaseJson<VideoDataBean> videoDataBeanBaseJson) throws Exception {
                        data = videoDataBeanBaseJson.getData().getData();
                        Log.d("photo data", "photo data " + data);
                        if (data != null) {
                            photoAdapter.setNewData(data);
                        }
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });

        ;
    }

    @Override
    public void onRefresh() {
        updateData();
    }
}
