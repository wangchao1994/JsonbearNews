package com.zf.hotupdate_tinker.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangchao on 17-10-30.
 */

public class HomeTabBean implements Serializable {

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
