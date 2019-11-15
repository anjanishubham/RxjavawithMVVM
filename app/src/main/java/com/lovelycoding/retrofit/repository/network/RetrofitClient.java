package com.lovelycoding.retrofit.repository.network;

import android.util.Log;

import java.net.URL;

import io.reactivex.android.plugins.RxAndroidPlugins;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String TAG = "RetrofitClient";
    private static int count =1;
    private static final String URL = "https://jsonplaceholder.typicode.com";
    private static Retrofit retrofit= new Retrofit.Builder()
                    .baseUrl(URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
    private static ApiService service = retrofit.create(ApiService.class);

    public static ApiService getService() {
        return service;
    }
}
