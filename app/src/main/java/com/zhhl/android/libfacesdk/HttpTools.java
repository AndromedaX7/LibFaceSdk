package com.zhhl.android.libfacesdk;


import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

//import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by miao on 2018/6/21.
 */
class HttpTools {
    private static OkHttpClient client;
    private static HashMap<Class<?>, Object> interfaces = new HashMap<>();

    private static OkHttpClient okHttpClient() {
        if (client == null) {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
            builder.connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS);
            client = builder.build();
        }
        return client;
    }

    public static <T> T build(Class<T> classes) {
        T target = (T) interfaces.get(classes);
        if (target == null) {
            target = new Retrofit.Builder()
                    .baseUrl("http://www.cczhhl.cn/")
//                    .baseUrl("http://192.168.1.131:8080/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient())
                    .build()
                    .create(classes);
            interfaces.put(classes, target);
        }
        return target;
    }

}
