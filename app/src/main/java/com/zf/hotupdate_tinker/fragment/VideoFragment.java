package com.zf.hotupdate_tinker.fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.zf.hotupdate_tinker.R;
import com.zf.hotupdate_tinker.data.BaseJson;
import com.zf.hotupdate_tinker.data.HomeTabBean;
import com.zf.hotupdate_tinker.net.Api;
import com.zf.hotupdate_tinker.net.QClient;
import com.zf.hotupdate_tinker.net.QService;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends BaseFragment {

    private MagicIndicator magic_indicator;
    private ViewPager viewPager;
    public static ArrayList<HomeTabBean> data;
    private CommonNavigatorAdapter commonNavigatorAdapter;
    private VideoPageAdapter videoPageAdapter;
    private int list_id;

    public static VideoFragment newInstance() {
        Bundle args = new Bundle();
        VideoFragment fragment = new VideoFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected View initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_video, null);
        magic_indicator = (MagicIndicator) view.findViewById(R.id.video_magic_indicator);
        viewPager = (ViewPager) view.findViewById(R.id.video_viewpager);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        updateTab();
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigatorAdapter = new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return data == null ? 0 : data.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.BLACK);
                colorTransitionPagerTitleView.setSelectedColor(Color.RED);
                String name = data.get(index).getName();
                if (name != null) {
                    colorTransitionPagerTitleView.setText(name);
                    colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            viewPager.setCurrentItem(index);
                            list_id = data.get(index).getList_id();
                        }
                    });

                    return colorTransitionPagerTitleView;
                }
                return null;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                return linePagerIndicator;
            }
        };
        commonNavigator.setAdapter(commonNavigatorAdapter);
        magic_indicator.setNavigator(commonNavigator);
        videoPageAdapter = new VideoPageAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(videoPageAdapter);
        ViewPagerHelper.bind(magic_indicator,viewPager);

    }

    class VideoPageAdapter extends FragmentStatePagerAdapter{

            public VideoPageAdapter(FragmentManager fm) {
                super(fm);
            }

            @Override
            public Fragment getItem(int position) {
                VideoDetailFragment videoDetailFragment = VideoDetailFragment.newInstance(data.get(position).getList_id());
                return videoDetailFragment;
            }

            @Override
            public int getCount() {
                return data == null ? 0 : data.size();
            }
        }

    /**
     * https://lf.snssdk.com/neihan/service/tabs/?essence=1&iid=14204866276&
     * device_id=38616036346&ac=wifi&channel=tengxun&aid=7&
     * app_name=joke_essay&version_code=651&version_name=6.5.1&device_platform=android&ssmix=a&
     * device_type=HUAWEI+C8818&device_brand=Huawei&os_api=19&os_version=4.4.4&
     * uuid=A00000599C2C37&openudid=36459bf17f34022b
     * &manifest_version_code=651&resolution=720*1280&dpi=320&update_version_code=6512
     */
    private void updateTab() {
/**
 *
 *
 * https://lf.snssdk.com/neihan/service/tabs/?
 * essence=1
 * &iid=14204866276
 * &device_id=38616036346&
 * ac=wifi&channel=tengxun
 * &aid=7&app_name=joke_essay
 * &version_code=651&version_name=6.5.1
 * &device_platform=android&ssmix=a
 * &device_type=HUAWEI+C8818&device_brand=Huawei
 * &os_api=19&os_version=4.4.4
 * &uuid=A00000599C2C37&openudid=36459bf17f34022b
 * &manifest_version_code=651&resolution=720*1280&dpi=320&update_version_code=6512
 * */
        QClient.getInstance().create(QService.class, Api.APP_DOMAIN)
                .getHomeTabs(
                        "1",
                        "14204866276",
                        "38616036346",
                        "wifi",
                        "tengxun",
                        "7",
                        "joke_essay",
                        "651",
                        "6.5.1",
                        "android",
                        "a",
                        "HUAWEI+C8818",
                        "Huawei",
                        "19",
                        "4.4.4",
                        "A00000599C2C37",
                        "36459bf17f34022b",
                        "651",
                        "720*1280",
                        "320",
                        "6512"
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseJson<ArrayList<HomeTabBean>>>() {
                    @Override
                    public void accept(BaseJson<ArrayList<HomeTabBean>> arrayListBaseJson) throws Exception {

                        if (arrayListBaseJson != null) {
                            data = arrayListBaseJson.getData();
                            commonNavigatorAdapter.notifyDataSetChanged();
                            videoPageAdapter.notifyDataSetChanged();
                            Log.d("video  data","data tabs====="+ data.get(0).getName());
                        }else{
                            Toast.makeText(getContext(),arrayListBaseJson.getMessage(),Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
    }
}
