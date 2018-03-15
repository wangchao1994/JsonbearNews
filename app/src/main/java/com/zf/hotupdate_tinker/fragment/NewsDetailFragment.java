package com.zf.hotupdate_tinker.fragment;

import android.content.Intent;
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
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.zf.hotupdate_tinker.R;
import com.zf.hotupdate_tinker.adpter.NewsAdapter;
import com.zf.hotupdate_tinker.common.Contants;
import com.zf.hotupdate_tinker.data.NewsData;
import com.zf.hotupdate_tinker.net.QClient;
import com.zf.hotupdate_tinker.net.QService;
import com.zf.hotupdate_tinker.WebViewActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by wangchao on 17-10-18.
 */

public class NewsDetailFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout srl;
    private RecyclerView recyclerView;
    private String type = null;
    private NewsAdapter newsAdapter;

    /*
        public NewsDetailFragment() {
        }

        public NewsDetailFragment(String type) {
            this.type = type;
        }
    */
    public static NewsDetailFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString("type", type);
        NewsDetailFragment fragment = new NewsDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getString("type");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    private View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_news_data, null);
        srl = (SwipeRefreshLayout) view.findViewById(R.id.srl);
        //设置下拉刷新
        srl.setColorSchemeColors(Color.RED, Color.RED);
        srl.setOnRefreshListener(this);
        newsAdapter = new NewsAdapter();
        newsAdapter.setEnableLoadMore(true);
        newsAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_new_detail);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(newsAdapter);
        recyclerView.addOnItemTouchListener(new SimpleClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getContext(), "1111", Toast.LENGTH_SHORT).show();
                Log.d("news position", "position====>" + position);

            }

            @Override
            public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                //Share
            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                NewsData.ResultBean.DataBean dataBean = (NewsData.ResultBean.DataBean) adapter.getItem(position);
                String url = dataBean.getUrl();
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);

            }

            @Override
            public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        updateData();
    }

    @Override
    public void onRefresh() {
        updateData();
    }

    /**
     * 更新数据
     */
    private void updateData() {

        srl.setRefreshing(true);
        QClient.getInstance()
                .create(QService.class, Contants.BASE_JUHE_URL)
                .getNewsData(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewsData>() {
                    @Override
                    public void accept(NewsData newsData) throws Exception {
                        if (newsData != null) {
                            Log.d("news data", "newsData----->" + newsData);
                            if (newsData.getResult().getData() != null) {
                                newsAdapter.setNewData(newsData.getResult().getData());
                            }
                        }
                        srl.setRefreshing(false);
                    }
                })
        ;

    }

    /*
    private void GetJson() {
        new Thread(new Runnable() {
            public HttpUrl url;

            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().get()
                        .url(url)
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String json = response.body().string();
                    }
                });
            }
        }).start();
    }
*/

}
