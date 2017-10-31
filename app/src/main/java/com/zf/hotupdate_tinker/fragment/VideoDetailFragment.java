package com.zf.hotupdate_tinker.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zf.hotupdate_tinker.R;
import com.zf.hotupdate_tinker.adpter.VideoAdapter;
import com.zf.hotupdate_tinker.common.Contants;
import com.zf.hotupdate_tinker.data.BaseJson;
import com.zf.hotupdate_tinker.data.HomeTabBean;
import com.zf.hotupdate_tinker.data.VideoDataBean;
import com.zf.hotupdate_tinker.net.Api;
import com.zf.hotupdate_tinker.net.QClient;
import com.zf.hotupdate_tinker.net.QService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoDetailFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private View view;
    private String type = null;
    private Long min_time;
    private  List<VideoDataBean.DataBean> dataBeen;
    private VideoAdapter videoAdapter;
    private ImageView iv_update;
    private VideoFragment videoFragment;
    private int list_id;

    public static VideoDetailFragment newInstance() {
        Bundle args = new Bundle();

        VideoDetailFragment fragment = new VideoDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public VideoDetailFragment(String type) {
        this.type = type;
    }
    public VideoDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return initView();
    }

    private View initView() {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_video_detail, null);
        swipeRefreshLayout = view.findViewById(R.id.srl_video);
        iv_update = (ImageView) view.findViewById(R.id.iv_update);
        iv_update.setOnClickListener(mUpdateListener);
        swipeRefreshLayout.setColorSchemeColors(Color.RED,Color.RED);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = view.findViewById(R.id.rv_video_detail);
        if (videoAdapter == null){
            videoAdapter = new VideoAdapter();
            videoAdapter.setEnableLoadMore(true);
            videoAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(videoAdapter);
        }
        return view;
    }

    private  View.OnClickListener mUpdateListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Animation opranimation = AnimationUtils.loadAnimation(getContext(),R.anim.rotate_in);
            LinearInterpolator linearInterpolator = new LinearInterpolator();
            opranimation.setInterpolator(linearInterpolator);
            view.startAnimation(opranimation);
            updateData();
        }
    };


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }
    /**
     *
     * 数据加载
     * */
    private void initData() {
        updateData();
    }


    /**
     * 数据的更新
     *https://lf.snssdk.com/neihan/stream/mix/v1/?
     * mpic=1&webp=1&essence=1&video_cdn_first=1
     * &fetch_activity=1&message_cursor=-1&am_longitude=108.933149&am_latitude=34.170875&
     * screen_width=720&double_col_mode=0
     * &local_request_tag=1503884417492&iid=14204866276&device_id=38616036346&ac=wifi&channel=tengxun
     * &aid=7&app_name=alan&version_code=651&version_name=6.5.1&device_platform=android&ssmix=a&device_type=HUAWEI+C8818&device_brand=Huawei&os_api=19&os_version=4.4.4&uuid=A00000599C2C37&openudid=36459bf17f34022b&manifest_version_code=651&resolution=720*1280&dpi=320&update_version_code=6512
     *
     * */
    private void updateData() {
//获取tab接口i数据返回的getList_id()

        swipeRefreshLayout.setRefreshing(true);
        if (videoFragment == null){
            videoFragment = new VideoFragment();
            list_id = videoFragment.List_id();
        }
        QClient.getInstance().create(QService.class, Api.APP_DOMAIN)
               // .getMainTab1ObjectData(VideoFragment.data.get(0).getList_id()+"","西安", (long) 108.9158414235, (long) 34.165824685598,
                .getMainTab1ObjectData(list_id + "", "西安", (long) 108.9158414235, (long) 34.165824685598,
                        new Date().getTime(),20,(long)1509353250//
        ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseJson<VideoDataBean>>() {
                    @Override
                    public void accept(BaseJson<VideoDataBean> videoDataBeanBaseJson) throws Exception {
                        VideoDataBean data = videoDataBeanBaseJson.getData();
                        if (data != null){
                            dataBeen = data.getData();
                            videoAdapter.setNewData(dataBeen);
                            Log.d("video data1","data 11111"+dataBeen.get(0).getGroup().getContent());
                            iv_update.clearAnimation();
                        }else{
                            //Toast.makeText(getContext(),"地址不合法",Toast.LENGTH_SHORT).show();
                        }
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });

    }

    @Override
    public void onRefresh() {
        updateData();
    }
}
