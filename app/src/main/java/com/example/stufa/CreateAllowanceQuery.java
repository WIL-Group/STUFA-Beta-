package com.example.stufa;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CreateAllowanceQuery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_allowance_query);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.create_a_query));
        //actionBar.setIcon(R.drawable.person);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

    }
}