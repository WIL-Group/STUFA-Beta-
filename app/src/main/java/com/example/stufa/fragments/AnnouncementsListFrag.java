package com.example.stufa.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.stufa.R;
import com.example.stufa.app_utilities.AnnouncementAdapter;
import com.example.stufa.app_utilities.FirebaseCRUDHelper;
import com.example.stufa.app_utilities.Utilities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class AnnouncementsListFrag extends Fragment {

    AnnouncementAdapter adapter;
    private RecyclerView rv;
    public ProgressBar mProgressBar;
    private FirebaseCRUDHelper crudHelper=new FirebaseCRUDHelper();
    private LinearLayoutManager layoutManager;
    View view;
    public AnnouncementsListFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.announcements_fragment_list, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rv = view.findViewById(R.id.aList);

        layoutManager = new LinearLayoutManager(this.getActivity(),LinearLayoutManager.HORIZONTAL,false);
        rv.setLayoutManager(layoutManager);
        adapter= new AnnouncementAdapter(this.getActivity(), Utilities.DataCache);
        crudHelper.select((AppCompatActivity) this.getActivity().getApplicationContext(), Utilities.getDatabaseRefence(), rv,adapter);
        rv.setAdapter(adapter);

    }
}