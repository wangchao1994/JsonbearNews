package com.zf.hotupdate_tinker.adpter;


import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zf.hotupdate_tinker.R;
import com.zf.hotupdate_tinker.data.VideoDataBean;

import java.util.List;


/**
 * Created by wangchao on 17-10-20.
 */

public class PhotoAdapter extends BaseQuickAdapter<VideoDataBean.DataBean, BaseViewHolder> {

    public PhotoAdapter() {
        super(R.layout.item_photo);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoDataBean.DataBean item) {
        ImageView imageView_user = helper.getView(R.id.photo_image);
        String cover_image_uri = item.getGroup().getUser().getAvatar_url();
        Glide.with(mContext).load(cover_image_uri).into(imageView_user);
        helper.setText(R.id.tv_photo_title, item.getGroup().getContent());
        helper.setText(R.id.photo_tv_username, item.getGroup().getUser().getName());

        ImageView imageView= (ImageView) helper.getView(R.id.iv_photo);

        VideoDataBean.DataBean.GroupBean.LargeCoverBean large_image = item.getGroup().getLarge_image();
        Log.d("photo adapter", "large_image" + large_image);
        List<VideoDataBean.DataBean.GroupBean.LargeCoverBean.UrlListBeanXXXX> url_list = large_image.getUrl_list();
        Log.d("photo adapter", "large_image" + url_list.get(0).getUrl());
        if (url_list != null && url_list.size() > 0) {
            for (int i = 0; i < url_list.size(); i++) {
                String url = url_list.get(i).getUrl();
                if (url != null) {
                    Glide.with(mContext).load(url).into(imageView);
                } else {
                    imageView.setImageResource(R.mipmap.ic_launcher);
                }
            }
        }
    }
}
