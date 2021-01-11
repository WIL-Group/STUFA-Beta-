package com.example.stufa.activities;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.stufa.R;
import com.example.stufa.app_utilities.AnnouncementAdapter;
import com.example.stufa.app_utilities.FirebaseCRUDHelper;
import com.example.stufa.app_utilities.QueryAdapter;
import com.example.stufa.app_utilities.Utilities;
import com.example.stufa.data_models.Announcement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AnnouncementBrowsing extends AppCompatActivity implements AnnouncementAdapter.ItemClickListener{

    AnnouncementAdapter adapter;
    ArrayList<Announcement> announcements;
    Announcement announcement;
    private RecyclerView rv;
    public ProgressBar mProgressBar;
    private FirebaseCRUDHelper crudHelper=new FirebaseCRUDHelper();
    private LinearLayoutManager layoutManager;

    DatabaseReference announcementRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.announcement_browsing);

        announcements = new ArrayList<>();
        rv = findViewById(R.id.aList);
        rv.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(AnnouncementBrowsing.this);
        rv.setLayoutManager(layoutManager);


        announcementRef = FirebaseDatabase.getInstance().getReference().child("Announcements");

        announcementRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                announcements.clear();

                for(DataSnapshot ds : snapshot.getChildren())
                {
                    announcement = ds.getValue(Announcement.class);
                    announcements.add(announcement);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Utilities.show(AnnouncementBrowsing.this, "Error! " + error);
            }
        });

        adapter = new AnnouncementAdapter(AnnouncementBrowsing.this,announcements);
        rv.setAdapter(adapter);
/*
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
        crudHelper.select(this, Utilities.getDatabaseRefence(), rv,adapter);
        rv.setAdapter(adapter);*/
    }

    @Override
    public void onItemClick(int pos) {



    }
}