package com.zf.hotupdate_tinker.net;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.zf.hotupdate_tinker.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wangchao on 17-10-18.
 */

public class QClient {

    public  static  QClient qClient;
    public  static OkHttpClient.Builder builder;
    public QClient() {
        initSetting();
    }

/**
 * 创建Qclient实例
 * */
    public static QClient getInstance() {
        if (qClient == null) {
            synchronized (QClient.class) {
                if (qClient == null) {
                    qClient = new QClient();
                }
            }
        }
        return qClient;
    }

    public <T> T create(Class<T> service,String  baseUrl){
        checkNotNull(service,"sevice is null");
        checkNotNull(baseUrl,"baseUrl is null");
        return new Retrofit.Builder().baseUrl(baseUrl)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(service);
    }

    /**
     * 检查对象是否为空
     * */
    private <T> T checkNotNull(T object, String s) {
        if (object == null){
            throw  new NullPointerException(s);
        }
        return object;
    }


    private void initSetting() {
        //初始化okhttp
        builder = new OkHttpClient.Builder()
                .connectTimeout(9, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS);
        if (BuildConfig.DEBUG){
            //如果是Debug模式，则添加日志拦截器
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
        }

    }
}
