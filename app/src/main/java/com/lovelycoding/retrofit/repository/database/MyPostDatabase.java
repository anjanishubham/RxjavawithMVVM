package com.lovelycoding.retrofit.repository.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.lovelycoding.retrofit.pojo.Posts;


@Database(entities = {Posts.class}, version = 1)
public abstract class MyPostDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "mypost.db";
    private static MyPostDatabase databaseInstance;

    public abstract PostDao getPostsDao();

    public static MyPostDatabase getDatabaseInstance(Context context) {

        if (databaseInstance == null) {
            databaseInstance = Room.databaseBuilder(context, MyPostDatabase.class, DATABASE_NAME).build();
        }
        return databaseInstance;
    }

}
