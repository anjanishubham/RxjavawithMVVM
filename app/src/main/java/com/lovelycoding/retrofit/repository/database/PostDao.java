package com.lovelycoding.retrofit.repository.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.lovelycoding.retrofit.pojo.Posts;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface PostDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(Posts... posts);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(Posts posts);

    @Query("SELECT *FROM Posts")
    Flowable<List<Posts>> getPostListFromDatabase();
}
