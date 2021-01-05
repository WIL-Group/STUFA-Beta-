package com.example.stufa;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class CreateQuery extends AppCompatActivity {

    CheckBox cbBookAllowance, cbMealAllowance, cbAccomodationOrTransportAllowance;
    EditText etQueryMessage;
    Button btnSave, btnDelete, btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_query);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.create_a_query));
        actionBar.setIcon(R.drawable.query);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        cbBookAllowance = findViewById(R.id.cbBookAllowance);
        cbMealAllowance = findViewById(R.id.cbMealAllowance);
        cbAccomodationOrTransportAllowance = findViewById(R.id.cbAccomodationOrTransportAllowance);
        etQueryMessage = findViewById(R.id.etQueryMessage);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
        btnSubmit = findViewById(R.id.btnSubmit);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}