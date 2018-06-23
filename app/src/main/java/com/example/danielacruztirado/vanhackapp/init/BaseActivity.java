package com.example.danielacruztirado.vanhackapp.init;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.danielacruztirado.vanhackapp.R;
import com.google.firebase.FirebaseApp;

public abstract class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initViews();
    }


    public abstract void initViews();
    public abstract @LayoutRes int getLayout();
}