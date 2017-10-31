package com.zf.hotupdate_tinker.data;

import java.util.List;

/**
 * Created by wangchao on 17-10-30.
 */

public class VideoTabBean {


    /**
     * message : success
     * data : [{"double_col_mode":false,"umeng_event":"moment","is_default_tab":false,"url":"http://lf.snssdk.com/neihan/dongtai/dongtai_list/v1/","list_id":-10001,"refresh_interval":1800,"type":3,"name":"关注"},{"double_col_mode":false,"umeng_event":"recommend","is_default_tab":true,"url":"http://lf.snssdk.com/neihan/stream/mix/v1/?content_type=-101","list_id":-101,"refresh_interval":1800,"type":1,"name":"推荐"},{"double_col_mode":false,"umeng_event":"video","is_default_tab":false,"url":"http://lf.snssdk.com/neihan/stream/mix/v1/?content_type=-104","list_id":-104,"refresh_interval":1800,"type":1,"name":"视频"},{"double_col_mode":false,"umeng_event":"live","is_default_tab":false,"url":"https://hotsoon.snssdk.com/hotsoon/feed/","list_id":-10002,"refresh_interval":300,"type":6,"name":"直播"},{"double_col_mode":false,"umeng_event":"pic","is_default_tab":false,"url":"http://lf.snssdk.com/neihan/stream/mix/v1/?content_type=-103","list_id":-103,"refresh_interval":1800,"type":1,"name":"图片"},{"double_col_mode":false,"umeng_event":"essay","is_default_tab":false,"url":"http://lf.snssdk.com/neihan/stream/mix/v1/?content_type=-102","list_id":-102,"refresh_interval":1800,"type":1,"name":"段子"},{"double_col_mode":false,"umeng_event":"essence","is_default_tab":false,"url":"http://ic.snssdk.com/neihan/in_app/essence_list/","list_id":-20002,"refresh_interval":1800,"type":4,"name":"精华"},{"double_col_mode":false,"umeng_event":"game","is_default_tab":false,"url":"https://ic.snssdk.com/game_channel/feed?from_page=essay_channel&app_type=1&platform=0","list_id":-20003,"refresh_interval":1800,"type":4,"name":"游戏"}]
     */

    private String message;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * double_col_mode : false
         * umeng_event : moment
         * is_default_tab : false
         * url : http://lf.snssdk.com/neihan/dongtai/dongtai_list/v1/
         * list_id : -10001
         * refresh_interval : 1800
         * type : 3
         * name : 关注
         */

        private boolean double_col_mode;
        private String umeng_event;
        private boolean is_default_tab;
        private String url;
        private int list_id;
        private int refresh_interval;
        private int type;
        private String name;

        public boolean isDouble_col_mode() {
            return double_col_mode;
        }

        public void setDouble_col_mode(boolean double_col_mode) {
            this.double_col_mode = double_col_mode;
        }

        public String getUmeng_event() {
            return umeng_event;
        }

        public void setUmeng_event(String umeng_event) {
            this.umeng_event = umeng_event;
        }

        public boolean isIs_default_tab() {
            return is_default_tab;
        }

        public void setIs_default_tab(boolean is_default_tab) {
            this.is_default_tab = is_default_tab;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getList_id() {
            return list_id;
        }

        public void setList_id(int list_id) {
            this.list_id = list_id;
        }

        public int getRefresh_interval() {
            return refresh_interval;
        }

        public void setRefresh_interval(int refresh_interval) {
            this.refresh_interval = refresh_interval;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
