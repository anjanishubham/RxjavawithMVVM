package com.lovelycoding.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lovelycoding.retrofit.ui.testing.TestingFragment;

public class Testing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testing_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, TestingFragment.newInstance())
                    .commitNow();
        }
    }
}
