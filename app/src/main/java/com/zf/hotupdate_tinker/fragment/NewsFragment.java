package com.zf.hotupdate_tinker.fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zf.hotupdate_tinker.MainActivity;
import com.zf.hotupdate_tinker.R;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends BaseFragment {

    private MagicIndicator magic_indicator;
    private ViewPager viewPager;
    private String types [] = null;
    private String[] types_cn = null;
    public NewsFragment() {
        // Required empty public constructor


    }

    public static NewsFragment newInstance() {
        Bundle args = new Bundle();
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_news, null);
        magic_indicator = (MagicIndicator) view.findViewById(R.id.magic_indicator);
        viewPager = (ViewPager) view.findViewById(R.id.main_viewpager);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        types = getResources().getStringArray(R.array.news_type_en);
        types_cn = getResources().getStringArray(R.array.news_type_cn);
        CommonNavigator commonNavigator = new CommonNavigator(context);
        CommonNavigatorAdapter commonNavigatorAdapter = new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return types == null ? 0 : types.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.BLACK);
                colorTransitionPagerTitleView.setSelectedColor(Color.RED);
                colorTransitionPagerTitleView.setText(types_cn[index]);
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
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
        NewsViewPagerAdapter newsViewPagerAdapter = new NewsViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(newsViewPagerAdapter);
        ViewPagerHelper.bind(magic_indicator,viewPager);
    }

    class NewsViewPagerAdapter extends FragmentStatePagerAdapter {

        public NewsViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            NewsDetailFragment detailFragment = NewsDetailFragment.newInstance(types[position]);
            return detailFragment;
        }

        @Override
        public int getCount() {
            return types == null ? 0 : types.length;
        }
    }
}
