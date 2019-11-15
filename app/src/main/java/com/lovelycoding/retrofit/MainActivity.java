package com.lovelycoding.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.lovelycoding.retrofit.adapter.MyAdapter;
import com.lovelycoding.retrofit.pojo.Posts;
import com.lovelycoding.retrofit.viewmodel.MainActivityViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.observers.SubscriberCompletableObserver;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView mRecycleView;
    ProgressDialog progressDialog;
    MyAdapter adapter;
    private MainActivityViewModel mViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading ");
       // progressDialog.show();
        mRecycleView=findViewById(R.id.customRecyclerView);
        mViewModel.createRepoInstance(this);
        Posts posts=new Posts(103902380,"dflkasdfkl","ajfkljadf alfdkajfdklajfd afdajflkfad");
        //mViewModel.saveDataIntoDatabse(posts);

        mViewModel.getAllPostFromDatabase().observe(this, new Observer<List<Posts>>() {
            @Override
            public void onChanged(List<Posts> posts) {
                for(Posts posts1:posts){
                    Log.d(TAG, "onChanged: database  "+posts1.toString());
                }
                initRecycleView(posts);

            }
        });
        mViewModel.getPostList().observe(this, new Observer<List<Posts>>() {
            @Override
            public void onChanged(List<Posts> posts) {
                if(posts.size()>0)
                {
                    progressDialog.dismiss();
                    for(Posts posts1:posts){

                        mViewModel.saveDataIntoDatabse(posts1);
                    }
                }
            }
        });

       // mViewModel.saveData(posts).observeOn(AndroidSchedulers.mainThread()).subscribe(new SubscriberCompletableObserver<>())

           /*   Call<List<Posts>> call = RetrofitClient.getService().getAllPosts();
              call.enqueue(new Callback<List<Posts>>() {
                  @Override
                  public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {

                      progressDialog.cancel();
                      generateDataList(response.body());
                  }

                  @Override
                  public void onFailure(Call<List<Posts>> call, Throwable t) {

                  }
              });*/

    }

    private void initRecycleView(List<Posts> body) {

        Log.d(TAG, "generateDataList: "+body.size());
        adapter=new MyAdapter(this,body);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        mRecycleView.setLayoutManager(layoutManager);
        mRecycleView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


}
