package com.example.stufa.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.stufa.R;

public class CreateBooking extends AppCompatActivity {

    CheckBox cbQueryRelatedBooking, cbGeneralBooking, cbRequestRelatedBooking;
    Button btnCreate, btnDelete, btnSubmit;
    TextView tvNameAndSurname, tvBookingType, tvDateCreated;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_booking);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.booking));
        actionBar.setIcon(R.drawable.booking);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        cbQueryRelatedBooking = findViewById(R.id.cbQueryRelatedBooking);
        cbGeneralBooking = findViewById(R.id.cbGeneralBooking);
        cbRequestRelatedBooking = findViewById(R.id.cbRequestRelatedBooking);
        btnCreate = findViewById(R.id.btnCreate);
        btnDelete = findViewById(R.id.btnDelete);
        btnSubmit = findViewById(R.id.btnSubmit);
        tvNameAndSurname = findViewById(R.id.tvNameAndSurname);
        tvBookingType = findViewById(R.id.tvBookingType);
        tvDateCreated = findViewById(R.id.tvDateCreated);

        btnCreate.setOnClickListener(new View.OnClickListener() {
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