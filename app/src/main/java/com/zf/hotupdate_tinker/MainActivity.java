package com.zf.hotupdate_tinker;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zf.hotupdate_tinker.fragment.BaseFragment;
import com.zf.hotupdate_tinker.fragment.JokeFragment;
import com.zf.hotupdate_tinker.fragment.NewsFragment;
import com.zf.hotupdate_tinker.fragment.PhotoFragment;
import com.zf.hotupdate_tinker.fragment.VideoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    private RadioGroup radioGroup;
    private List<BaseFragment> fragments;
    private RadioButton news;
    private RadioButton joke;
    private RadioButton photo;
    private RadioButton video;
    private Fragment currentFragment;
    private NewsFragment newsFragment;
    private JokeFragment jokeFragment;
    private PhotoFragment photoFragment;
    private VideoFragment videoFragment;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {

        radioGroup = (RadioGroup) findViewById(R.id.home_radiogroup);
        radioGroup.setOnCheckedChangeListener(this);
        news = (RadioButton) findViewById(R.id.news);
        joke = (RadioButton) findViewById(R.id.joke);
        photo = (RadioButton) findViewById(R.id.photo);
        video = (RadioButton) findViewById(R.id.video);
        newsFragment = new NewsFragment();
        jokeFragment = new JokeFragment();
        photoFragment = new PhotoFragment();
        videoFragment = new VideoFragment();
    }

    private void initData() {
        fragments = new ArrayList<>();
        fragments.add(newsFragment);
        fragments.add(jokeFragment);
        fragments.add(photoFragment);
        fragments.add(videoFragment);
        radioGroup.check(R.id.news);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        switch (i){
            case R.id.news:
                position = 0;
                break;
            case R.id.joke:
                position = 1;
                break;
            case R.id.photo:
                position = 2;
                break;
            case R.id.video:
                position = 3;
                break;
        }
        Fragment fragment = getFragemnt();
        switchFragment(currentFragment,fragment);
    }

    private void switchFragment(Fragment from, Fragment to) {
        if (from != to){
            currentFragment = to;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            if (!to.isAdded()){
                if (from != null){
                    fragmentTransaction.hide(from);
                }

                if (to != null){
                    fragmentTransaction.add(R.id.home_fl_content,to).commit();
                }
            }else{
                if (from != null){
                    fragmentTransaction.hide(from);
                }
                if (to != null){
                    fragmentTransaction.show(to).commit();
                }
            }
        }
    }
    private Fragment getFragemnt() {
        BaseFragment fragment = fragments.get(position);
        return fragment;
    }


}
