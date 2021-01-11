package com.example.stufa.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.stufa.R;
import com.example.stufa.activities.StudentHomePage;
import com.example.stufa.app_utilities.AnnouncementAdapter;
import com.example.stufa.app_utilities.FirebaseCRUDHelper;
import com.example.stufa.app_utilities.Utilities;
import com.example.stufa.data_models.Announcement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class AnnouncementsListFrag extends Fragment {

    AnnouncementAdapter adapter;
    private RecyclerView rv;
    DatabaseReference databaseReference;
    LinearLayoutManager layoutManager;

    ArrayList<Announcement> announcements;
    Announcement announcement;
    View view;
    public AnnouncementsListFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.announcements_fragment_list, container, false);

       /* rv = view.findViewById(R.id.announcements_list);
        rv.setHasFixedSize(true);
        announcements = new ArrayList<>();

        layoutManager = new LinearLayoutManager(getContext());

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Announcements");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                announcements.clear();

                for (DataSnapshot ds : snapshot.getChildren())
                {
                    announcement = ds.getValue(Announcement.class);
                    announcements.add(announcement);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        adapter = new AnnouncementAdapter(getContext(),announcements);
        rv.setAdapter(adapter);
*/
        return view;
    }

   /* @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rv = view.findViewById(R.id.aList);

        layoutManager = new LinearLayoutManager(this.getActivity(),LinearLayoutManager.HORIZONTAL,false);
        rv.setLayoutManager(layoutManager);
        adapter= new AnnouncementAdapter(this.getActivity(), Utilities.DataCache);
        crudHelper.select((AppCompatActivity) this.getActivity().getApplicationContext(), Utilities.getDatabaseRefence(), rv,adapter);
        rv.setAdapter(adapter);

    }*/
}