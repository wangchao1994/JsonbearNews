package com.zf.hotupdate_tinker.adpter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zf.hotupdate_tinker.R;
import com.zf.hotupdate_tinker.data.VideoDataBean;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by wangchao on 17-10-30.
 */

public class VideoAdapter extends BaseQuickAdapter<VideoDataBean.DataBean,BaseViewHolder> {
    public VideoAdapter() {
        super(R.layout.item_video);
    }
    @Override
    protected void convert(BaseViewHolder helper, VideoDataBean.DataBean item) {
        String name = item.getGroup().getUser().getName();
        helper.setText(R.id.tv_username,name);
        CircleImageView iv = (CircleImageView) helper.getView(R.id.video_image);
        if (iv != null){
            Glide.with(mContext).load(item.getGroup().getUser().getAvatar_url())
                    .into(iv);
        }
        helper.setText(R.id.tv_title,item.getGroup().getContent());

        /**
         * 视频加载
         * */
        JCVideoPlayerStandard jcVideoPlayerStandard = (JCVideoPlayerStandard) helper.getView(R.id.videoplay_content);
        String download_url = item.getGroup().getDownload_url();
        if (download_url == null) return;
        jcVideoPlayerStandard.setUp(download_url, JCVideoPlayer.SCREEN_LAYOUT_LIST,item.getGroup().getPlay_count(),"人观看");
        Glide.with(mContext).load(item.getGroup().getLarge_cover().getUrl_list().get(0).getUrl()).into(jcVideoPlayerStandard.thumbImageView);
    }
}
