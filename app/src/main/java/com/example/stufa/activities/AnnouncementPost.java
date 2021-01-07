package com.example.stufa.activities;

import android.os.Bundle;
import android.widget.EditText;

import com.example.stufa.R;

import androidx.appcompat.app.AppCompatActivity;

public class AnnouncementPost extends AppCompatActivity {

    EditText etTitle, etMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_post);
        etTitle = findViewById(R.id.etTitle);
        etMessage = findViewById(R.id.etMessage);
    }
}