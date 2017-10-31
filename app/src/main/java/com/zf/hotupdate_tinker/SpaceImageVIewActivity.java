package com.zf.hotupdate_tinker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import cn.bluemobi.dylan.photoview.library.PhotoView;

public class SpaceImageVIewActivity extends AppCompatActivity {
    private PhotoView imageViewSpace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_image_view);
        imageViewSpace = (PhotoView) findViewById(R.id.image_space);
        String url = getIntent().getExtras().getString("url");
        if (url != null) {
            Glide.with(this).load(url).into(imageViewSpace);
        }
    }
}
