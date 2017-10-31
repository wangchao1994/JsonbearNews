package com.zf.hotupdate_tinker.adpter;


import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zf.hotupdate_tinker.R;
import com.zf.hotupdate_tinker.data.PhotoData;


/**
 * Created by wangchao on 17-10-20.
 */

public class PhotoAdapter extends BaseQuickAdapter<PhotoData,BaseViewHolder>{

    public PhotoAdapter() {
        super(R.layout.item_photo);
    }

    @Override
    protected void convert(BaseViewHolder helper, PhotoData item) {
        ImageView imageView= (ImageView) helper.getView(R.id.iv_photo);
        imageView.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public int getItemCount() {
        return 20;
    }
}
