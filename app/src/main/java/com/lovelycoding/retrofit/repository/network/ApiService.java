package com.lovelycoding.retrofit.repository.network;

import androidx.lifecycle.LiveData;

import com.lovelycoding.retrofit.pojo.Posts;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/posts")
    Flowable<List<Posts>> getAllPosts();

    @GET("/posts")
    Call<List<Posts>> getAllPost();
}
