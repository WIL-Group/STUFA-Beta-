package com.example.stufa;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

public class CreateRequest extends AppCompatActivity {

    Button btnViewFinancialStatement, btnRequestFinancialClearance, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_request);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.create_a_request));
        //actionBar.setIcon(R.drawable.person);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

    }
}