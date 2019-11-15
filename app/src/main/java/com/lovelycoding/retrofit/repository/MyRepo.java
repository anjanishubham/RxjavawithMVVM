package com.lovelycoding.retrofit.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;

import com.lovelycoding.retrofit.pojo.Posts;
import com.lovelycoding.retrofit.repository.database.MyPostDatabase;
import com.lovelycoding.retrofit.repository.network.ApiService;
import com.lovelycoding.retrofit.repository.network.RetrofitClient;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;



public class MyRepo {
    private static final String TAG = "MyRepo";
    private static MyRepo instance;
    private static ApiService service;
    private static ExecutorService executors;
    private MyPostDatabase mDatabase;
   // private Context context;

    public static  MyRepo getInstance(Context context) {
        if (instance == null) {
            instance=new MyRepo(context);
        }
        return instance;
    }

    public MyRepo(Context context) {
        executors=Executors.newSingleThreadExecutor();
        service= RetrofitClient.getService();
        mDatabase=MyPostDatabase.getDatabaseInstance(context);
    }



    public LiveData<List<Posts>> getPostList(){
        List<Posts> mlist = new ArrayList<>();
        LiveData<List<Posts>> objectLiveData =LiveDataReactiveStreams.fromPublisher(service.getAllPosts().subscribeOn(Schedulers.io()));
        return objectLiveData;
    }




}
