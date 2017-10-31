package com.zf.hotupdate_tinker.net;

import com.zf.hotupdate_tinker.data.BaseJson;
import com.zf.hotupdate_tinker.data.HomeTabBean;
import com.zf.hotupdate_tinker.data.JokeBean;
import com.zf.hotupdate_tinker.data.NewsData;
import com.zf.hotupdate_tinker.data.VideoDataBean;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by wangchao on 17-10-18.
 */

public interface QService {
    public static final String DESC = "desc";
    public static final String ASC = "asc";

    /**
     * 根据 新闻类型 获取新闻数据
     *
     * @param type  新闻的类型
     * @return      查询结束 返回 数据的 被观察者
     */
    // http://v.juhe.cn/toutiao/index?key=d78b502268f7456b79fbe7228cecdd46
    @GET("toutiao/index?key=d78b502268f7456b79fbe7228cecdd46")
    Observable<NewsData> getNewsData(
            @Query("type") String type
    );
    /**
     * @param page      查询的页数
     * @param pagesize  一页数据显示的条数
     * @return          查询结束返回的被观察者
     */
    // http://japi.juhe.cn/joke/content/text.from?key=ae240f7fba620fc370b803566654949e&page=2&pagesize=10
    // http://japi.juhe.cn/joke/content/text.from?key=bc061d4246c19d108027417b1e2ba805&page=2&pagesize=10
    @POST("text.from?key=ae240f7fba620fc370b803566654949e")
    Observable<JokeBean> getCurrentJokeData(
            @Query("page") int page,
            @Query("pagesize") int pagesize
    );
    /**
     * @param time          要指定查询的时间
     * @param page          查询的页数
     * @param pagesize      一页数据显示的条数
     * @param sort          判断是在指定时间之前还是之后
     *                          {@value DESC 指定之前},{@value ASC 指定之后}
     * @return              查询结束返回的被观察者
     */
    //http://japi.juhe.cn/joke/content/list.from?key=bc061d4246c19d108027417b1e2ba805&page=2&pagesize=10&sort=asc&time=1418745237
    // http://japi.juhe.cn/joke/content/list.from?key=ae240f7fba620fc370b803566654949e&page=1&pagesize=5&sort=desc
    @GET("list.from?key=ae240f7fba620fc370b803566654949e")
    Observable<JokeBean> getAssignJokeData(
            @Query("time") long time,
            @Query("page") int page,
            @Query("pagesize") int pagesize,
            @Query("sort") String sort
    );


    //video 内涵
//https://lf.snssdk.com/neihan/service/personal_tabs/?essence=1&iid=14204866276&device_id=38616036346&ac=wifi&channel=tengxun&aid=7&app_name=joke_essay&version_code=651&version_name=6.5.1&device_platform=android&ssmix=a&device_type=HUAWEI+C8818&device_brand=Huawei&os_api=19&os_version=4.4.4&uuid=A00000599C2C37&openudid=36459bf17f34022b&manifest_version_code=651&resolution=720*1280&dpi=320&update_version_code=6512

    /**
     * 获取首页tabs
     *
     * @return
     */
    @Headers({"Domain-Name: tabs"})
    //@GET("/neihan/service/tabs/?essence=1&iid=14204866276&device_id=38616036346&ac=wifi&channel=tengxun&aid=7&app_name=joke_essay&version_code=651&version_name=6.5.1&device_platform=android&ssmix=a&device_type=HUAWEI+C8818&device_brand=Huawei&os_api=19&os_version=4.4.4&uuid=A00000599C2C37&openudid=36459bf17f34022b&manifest_version_code=651&resolution=720*1280&dpi=320&update_version_code=6512")
    @GET("/neihan/service/tabs/?")
    Observable<BaseJson<ArrayList<HomeTabBean>>> getHomeTabs(
            @Query("essence") String essence,
            @Query("iid") String iid,
            @Query("device_id") String device_id,
            @Query("ac") String ac,
            @Query("channel") String channel,
            @Query("aid") String aid,
            @Query("app_name") String app_name,
            @Query("version_code") String version_code,
            @Query("version_name") String version_name,
            @Query("device_platform") String device_platform,
            @Query("ssmix") String ssmix,
            @Query("device_type") String device_type,
            @Query("device_brand") String device_brand,
            @Query("os_api") String os_api,
            @Query("os_version") String os_version,
            @Query("uuid") String uuid,
            @Query("openudid") String openudid,
            @Query("manifest_version_code") String manifest_version_code,
            @Query("resolution") String resolution,
            @Query("dpi") String dpi,
            @Query("update_version_code") String update_version_code
    );

    @Headers({"Domain-Name: tabs"})
    @GET("/neihan/service/personal_tabs/?essence=1&iid=14204866276&device_id=38616036346&ac=wifi&channel=tengxun&aid=7&app_name=joke_essay&version_code=651&version_name=6.5.1&device_platform=android&ssmix=a&device_type=HUAWEI+C8818&device_brand=Huawei&os_api=19&os_version=4.4.4&uuid=A00000599C2C37&openudid=36459bf17f34022b&manifest_version_code=651&resolution=720*1280&dpi=320&update_version_code=6512")
    Observable<BaseJson<ArrayList<HomeTabBean>>> getTab2Tabs();
    /**
     * 获取首页各个小Tab下的数据
     *
     * @param content_type 标签编号  由请求tab的接口返回  字段:list_id
     * @param city         当前城市
     * @param longitude    经纬度
     * @param latitude
     * @param am_loc_time  当前时间毫秒
     * @param count        请求的条数
     * @param min_time     上次刷新的时间
     * @return
     */
    @Headers({"Domain-Name: tabsData"})
    @GET("/neihan/stream/mix/v1/?mpic=1&webp=1&essence=1&video_cdn_first=1&fetch_activity=1&message_cursor=-1&am_longitude=108.933149&am_latitude=34.170875&screen_width=720&double_col_mode=0&local_request_tag=1503884417492&iid=14204866276&device_id=38616036346&ac=wifi&channel=tengxun&aid=7&app_name=alan&version_code=651&version_name=6.5.1&device_platform=android&ssmix=a&device_type=HUAWEI+C8818&device_brand=Huawei&os_api=19&os_version=4.4.4&uuid=A00000599C2C37&openudid=36459bf17f34022b&manifest_version_code=651&resolution=720*1280&dpi=320&update_version_code=6512")
    Observable<BaseJson<VideoDataBean>> getMainTab1ObjectData(@Query("content_type") String content_type,
                                                              @Query("am_city") String city,
                                                              @Query("longitude") Long longitude,
                                                              @Query("latitude") Long latitude,
                                                              @Query("am_loc_time") Long am_loc_time,
                                                              @Query("count") int count,
                                                              @Query("min_time") Long min_time);
}
