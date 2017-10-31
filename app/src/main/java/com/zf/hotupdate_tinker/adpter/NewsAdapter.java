package com.zf.hotupdate_tinker.adpter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zf.hotupdate_tinker.R;
import com.zf.hotupdate_tinker.data.NewsData;

/**
 * Created by wangchao on 17-10-18.
 */

public class NewsAdapter extends BaseQuickAdapter<NewsData.ResultBean.DataBean,BaseViewHolder> {

    public NewsAdapter() {
        super(R.layout.item_news_detail);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsData.ResultBean.DataBean item) {
        helper.setText(R.id.tv_news_detail_title,item.getTitle());
        helper.setText(R.id.tv_news_detail_author_name,item.getAuthor_name());
        helper.setText(R.id.tv_news_detail_date,item.getDate());
        ImageView iv = helper.getView(R.id.iv_news_detail_pic);
        Glide.with(mContext).load(item.getThumbnail_pic_s()).into(iv);
        helper.addOnClickListener(R.id.ll_news_detail);
    }
}
