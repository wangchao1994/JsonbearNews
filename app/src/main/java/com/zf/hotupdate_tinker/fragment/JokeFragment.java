package com.zf.hotupdate_tinker.fragment;


import android.graphics.Color;
import android.graphics.ImageFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zf.hotupdate_tinker.R;
import com.zf.hotupdate_tinker.adpter.JokeAdapter;
import com.zf.hotupdate_tinker.common.Contants;
import com.zf.hotupdate_tinker.data.JokeBean;
import com.zf.hotupdate_tinker.net.QClient;
import com.zf.hotupdate_tinker.net.QService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class JokeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{
    private LinearLayout ll_loading;
    private LinearLayout ll_error;
    private SwipeRefreshLayout srl;
    private TextView tv_joke_load_aging;
    private RecyclerView recyclerview;
    private JokeAdapter jokeAdapter;
    private int CurrentCounter = 0;
    private int TotalCounter = 5;
    public JokeFragment() {
        // Required empty public constructor
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0x01:
                    srl.setRefreshing(false);
                    ll_loading.setVisibility(View.GONE);
                    ll_error.setVisibility(View.GONE);
                    break;
                case 0x02:
                    break;
            }
        }
    };
    public static JokeFragment newInstance() {
        Bundle args = new Bundle();
        JokeFragment fragment = new JokeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected View initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_joke, null);
        ll_loading = view.findViewById(R.id.ll_loading);
        ll_error = view.findViewById(R.id.ll_error);
        tv_joke_load_aging = view.findViewById(R.id.tv_joke_load_again);
        srl = view.findViewById(R.id.srl_joke);
        srl.setColorSchemeColors(Color.RED,Color.RED);
        srl.setOnRefreshListener(this);
        recyclerview = view.findViewById(R.id.rv_joke);
        return view;
    }
    @Override
    protected void initData() {
        super.initData();

        jokeAdapter = new JokeAdapter();
        if (jokeAdapter != null){
            jokeAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setAdapter(jokeAdapter);
        //加载更多
        jokeAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (CurrentCounter >= TotalCounter){
                    jokeAdapter.loadMoreEnd();
                }else{
                    if (jokeAdapter.getItem(0) == null){
                        return;
                    }
                    long unxitime = (long)(jokeAdapter.getItem(jokeAdapter.getItemCount() - 2)).getUnixtime();
                    QClient.getInstance()
                            .create(QService.class,Contants.BASE_JOKE_URL)
                            .getAssignJokeData(unxitime,1,5,QService.DESC)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<JokeBean>() {
                                @Override
                                public void accept(JokeBean jokeBean) throws Exception {
                                    List<JokeBean.ResultBean.DataBean> data = jokeBean.getResult().getData();
                                    if (data != null){
                                        jokeAdapter.addData(data);
                                        CurrentCounter = TotalCounter;
                                        TotalCounter += 5;
                                        jokeAdapter.loadMoreComplete();
                                    }
                                }
                            });
                }
            }
        },recyclerview);

        tv_joke_load_aging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
            }
        });
        //界面初始化开启数据刷新
        updateData();

    }

    @Override
    public void onRefresh() {
        updateData();
    }


    private void updateData() {
        srl.setRefreshing(true);
        srl.setVisibility(View.VISIBLE);
        ll_error.setVisibility(View.GONE);
        ll_loading.setVisibility(View.VISIBLE);

 /*
        QClient.getInstance().create(QService.class, Contants.BASE_JOKE_URL)
                .getCurrentJokeData(1,8)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<JokeBean>() {
                    @Override
                    public void accept(JokeBean jokeBean) throws Exception {
                        List<JokeBean.ResultBean.DataBean> data = jokeBean.getResult().getData();
                        Log.d("joke data","joke"+data);
                        if (data != null){
                            jokeAdapter.setNewData(data);
                            handler.sendEmptyMessage(0x01);
                        }else{
                            Toast.makeText(getActivity().getApplicationContext(),"No data to show!",Toast.LENGTH_LONG).show();
                        }
                    }
                });*/


        new Thread(new Runnable() {
            @Override
            public void run() {
                QClient.getInstance().create(QService.class, Contants.BASE_JOKE_URL)
                        .getCurrentJokeData(1,8)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<JokeBean>() {
                            @Override
                            public void accept(JokeBean jokeBean) throws Exception {
                                if (jokeBean != null) return;
                                List<JokeBean.ResultBean.DataBean> data = jokeBean.getResult().getData();
                                Log.d("joke data","joke"+data);
                                if (data != null){
                                    jokeAdapter.setNewData(data);
                                    handler.sendEmptyMessage(0x01);
                                } else {
                                    Toast.makeText(getActivity().getApplicationContext(), "No data to show!", Toast.LENGTH_LONG).show();
                                }

                            }
                        });
            }
        }).start();

    }

}
