package com.zf.hotupdate_tinker.adpter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zf.hotupdate_tinker.R;
import com.zf.hotupdate_tinker.data.JokeBean;


public class JokeAdapter extends BaseQuickAdapter<JokeBean.ResultBean.DataBean, BaseViewHolder> {

    public JokeAdapter() {
        super(R.layout.item_joke);
    }

    @Override
    protected void convert(BaseViewHolder helper, JokeBean.ResultBean.DataBean item) {
        helper.setText(R.id.tv_joke_content, item.getContent());
        helper.setText(R.id.tv_joke_date, item.getUpdatetime());
    }

}
