package com.lovelycoding.retrofit.repository.datamanager;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;

import com.lovelycoding.retrofit.pojo.Posts;
import com.lovelycoding.retrofit.repository.database.MyPostDatabase;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class MyDataManager {
    Context context;
    private MyPostDatabase mDatabase;

    public MyDataManager(Context context) {
        this.context = context;
        mDatabase = MyPostDatabase.getDatabaseInstance(context);
    }

    public void addDataIntoDatabase(final DataCallback dataCallback, final Posts posts) {

        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                mDatabase.getPostsDao().insert(posts);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                dataCallback.dataAdded();
            }

            @Override
            public void onError(Throwable e) {
                dataCallback.errorAdded();
            }
        });
    }

    public Completable addData(Posts posts) {

        Completable completable=mDatabase.getPostsDao().insert(posts).subscribeOn(Schedulers.io());
        return completable;
    }


    // public
    public LiveData<List<Posts>> getAllPostFromDatabase() {
        LiveData<List<Posts>> postsLiveData= LiveDataReactiveStreams.fromPublisher(mDatabase.getPostsDao().getPostListFromDatabase()
        .subscribeOn(Schedulers.io()));
        return postsLiveData;

    }
}
