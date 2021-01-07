package com.example.stufa.app_utilities;

import android.util.Log;
import android.widget.ProgressBar;

import com.example.stufa.activities.AnnouncementBrowsing;
import com.example.stufa.data_models.Announcement;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class FirebaseCRUDHelper
{
    public void insert(final AppCompatActivity a,
                       final DatabaseReference databaseRef,
                       final ProgressBar pb, final Announcement announcement) {
        if (announcement == null)
        {
            Utilities.show(a,"VALIDATION FAILED Announcement is null");
            return;
        }
        else
        {
            Utilities.showProgressBar(pb);
            databaseRef.child("Announcements").push().setValue(announcement).
                    addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Utilities.hideProgressBar(pb);

                            if(task.isSuccessful()){
                                Utilities.openActivity(a, AnnouncementBrowsing.class);
                                Utilities.show(a,"Congrats! INSERT SUCCESSFUL");
                            }else{
                                Utilities.show(a,"UNSUCCESSFUL" + task.getException().
                                        getMessage());
                            }
                        }

                    });
        }
    }
    public void select(final AppCompatActivity a, DatabaseReference db,
                       final ProgressBar pb,
                       final RecyclerView rv, AnnouncementAdapter adapter)
    {
        Utilities.showProgressBar(pb);

        db.child("Announcements");

        db.child("Scientists").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //DataCache.clear();
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        //Now get Scientist Objects and populate our arraylist.
                        Announcement announcement = ds.getValue(Announcement.class);
                        announcement.setKey(ds.getKey());
                        //DataCache.add(scientist);
                    }
                    adapter.notifyDataSetChanged();
                    new Handler() {
                        public void post(Runnable runnable) {
                        }

                        @Override
                        public void publish(LogRecord logRecord) {
                            
                        }

                        @Override
                        public void flush() {

                        }

                        @Override
                        public void close() throws SecurityException {

                        }
                    }.post(new Runnable() {
                        @Override
                        public void run() {
                            Utilities.hideProgressBar(pb);
                           // rv.smoothScrollToPosition(DataCache.size());
                        }
                    });
                }
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    //Now get Scientist Objects and populate our arraylist.
                    Announcement scientist = ds.getValue(Announcement.class);
                    scientist.setKey(ds.getKey());
                    //DataCache.add(scientist);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("FIREBASE CRUD", error.getMessage());
                Utilities.hideProgressBar(pb);
                Utilities.show(a, "CANCELLED" + error.getMessage());
            }
            });
        }
    public void update(final AppCompatActivity a,
                       final DatabaseReference mDatabaseRef,
                       final ProgressBar pb,
                       final Announcement oldAnnouncement,
                       final Announcement newAnnouncement) {

    }
}
