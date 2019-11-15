package com.lovelycoding.retrofit.viewmodel;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lovelycoding.retrofit.pojo.Posts;
import com.lovelycoding.retrofit.repository.MyRepo;
import com.lovelycoding.retrofit.repository.datamanager.DataCallback;
import com.lovelycoding.retrofit.repository.datamanager.MyDataManager;

import java.util.List;

import io.reactivex.Completable;

public class MainActivityViewModel extends ViewModel implements DataCallback {
    private static MyRepo repoInstance;
    private Context context;
    private MyDataManager myDataManager;
   public void createRepoInstance(Context context) {
        this.context=context;
        repoInstance=MyRepo.getInstance(context.getApplicationContext());
        myDataManager=new MyDataManager(context);


    }


    public void saveDataIntoDatabse(Posts posts){
       myDataManager.addDataIntoDatabase(this,posts);

    }

    public LiveData<List<Posts>> getAllPostFromDatabase() {
      return myDataManager.getAllPostFromDatabase();
    }

    public  Completable saveData(Posts posts) {
        return myDataManager.addData(posts);
    }


    public LiveData<List<Posts>> getPostList() {
        return repoInstance.getPostList();
    }

    @Override
    public void dataAdded() {
        Toast.makeText(context, "Data Added !!!!!!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorAdded() {
        Toast.makeText(context, "getting error ", Toast.LENGTH_SHORT).show();
    }


}
