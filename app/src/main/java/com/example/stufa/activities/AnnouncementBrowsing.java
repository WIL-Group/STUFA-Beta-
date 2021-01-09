package com.example.stufa.activities;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.stufa.R;
import com.example.stufa.app_utilities.AnnouncementAdapter;
import com.example.stufa.app_utilities.FirebaseCRUDHelper;
import com.example.stufa.app_utilities.Utilities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AnnouncementBrowsing extends AppCompatActivity {
    AnnouncementAdapter adapter;
    private RecyclerView rv;
    public ProgressBar mProgressBar;
    private FirebaseCRUDHelper crudHelper=new FirebaseCRUDHelper();
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.announcement_browsing);

//        mProgressBar = findViewById(R.id.mProgressBarLoad);
//        mProgressBar.setIndeterminate(true);
//        Utils.showProgressBar(mProgressBar);
        rv = findViewById(R.id.aList);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(),
                layoutManager.getOrientation());
        rv.addItemDecoration(dividerItemDecoration);
        adapter= new AnnouncementAdapter(this, Utilities.DataCache);
        rv.setAdapter(adapter);
    }

}