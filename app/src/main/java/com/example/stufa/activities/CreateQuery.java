package com.example.stufa.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.stufa.R;
import com.example.stufa.app_utilities.QueryAdapter;
import com.example.stufa.data_models.Query;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CreateQuery extends AppCompatActivity implements QueryAdapter.ItemClicked  {

    CheckBox cbBookAllowance, cbMealAllowance, cbAccommodationOrTransportAllowance;
    EditText etQueryMessage;
    Button btnSave, btnDelete, btnSubmit;

    RecyclerView recyclerView;
    private QueryAdapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    private ArrayList<Query> queries;
    Query query;
    String type, message, userID;
    DatabaseReference queryReff;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_query);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(getString(R.string.create_a_query));
        actionBar.setIcon(R.drawable.query);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        cbBookAllowance = findViewById(R.id.cbBookAllowance);
        cbMealAllowance = findViewById(R.id.cbMealAllowance);
        cbAccommodationOrTransportAllowance = findViewById(R.id.cbAccomodationOrTransportAllowance);
        etQueryMessage = findViewById(R.id.etQueryMessage);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
        btnSubmit = findViewById(R.id.btnSubmit);
        queries = new ArrayList<>();

        recyclerView = findViewById(R.id.rvList);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(CreateQuery.this);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new QueryAdapter(queries,CreateQuery.this);
        recyclerView.setAdapter(myAdapter);

        queryReff = FirebaseDatabase.getInstance().getReference().child("saved_queries");

        queryReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                queries.clear();

                for(DataSnapshot ds : snapshot.getChildren())
                {
                    query = ds.getValue(Query.class);
                    queries.add(query);
                }
                myAdapter = new QueryAdapter(queries,CreateQuery.this);
                recyclerView.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnSave.setOnClickListener(v -> {
            insertData();
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(query == null)
                {
                    Toast.makeText(CreateQuery.this,"Please select a query to delete", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    queryReff = FirebaseDatabase.getInstance().getReference("saved_queries").child("");
                    Task<Void> mTask = queryReff.removeValue();

                    mTask.addOnSuccessListener(aVoid ->
                            Toast.makeText(CreateQuery.this, "Removed!",Toast.LENGTH_LONG).show()).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(CreateQuery.this,"Failed! ", Toast.LENGTH_LONG).show();
                        }
                    });
                    queries.remove(query);
                }
                myAdapter = new QueryAdapter(queries,CreateQuery.this);
                recyclerView.setAdapter(myAdapter);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    public void onItemClicked(int index) {
        query = queries.get(index);

    }

    public void insertData()
    {
        if(cbBookAllowance.isChecked())
        {
            type = cbBookAllowance.getText().toString().trim();

            if(etQueryMessage.getText().toString().isEmpty())
            {
                Toast.makeText(CreateQuery.this, "Please enter query message", Toast.LENGTH_SHORT).show();
            }
            else
            {
                message = etQueryMessage.getText().toString().trim();
                query = new Query(type, message);
                queries.add(query);
                queryReff.push().setValue(query);
                myAdapter = new QueryAdapter(queries,CreateQuery.this);
                recyclerView.setAdapter(myAdapter);
            }
        }
        else if(cbMealAllowance.isChecked())
        {
            type = cbMealAllowance.getText().toString().trim();

            if(etQueryMessage.getText().toString().isEmpty())
            {
                Toast.makeText(CreateQuery.this, "Please enter query message", Toast.LENGTH_SHORT).show();
            }
            else
            {
                message = etQueryMessage.getText().toString().trim();

                query = new Query(type, message);
                queryReff.push().setValue(query);
                Toast.makeText(CreateQuery.this, "Query successfully added", Toast.LENGTH_SHORT).show();
            }
        }
        else if(cbAccommodationOrTransportAllowance.isChecked())
        {
            type = cbAccommodationOrTransportAllowance.getText().toString().trim();
            if(etQueryMessage.getText().toString().isEmpty())
            {
                Toast.makeText(CreateQuery.this, "Please enter query message", Toast.LENGTH_SHORT).show();
            }
            else
            {
                message = etQueryMessage.getText().toString().trim();

                query = new Query(type, message);
                queryReff.push().setValue(query);

                Toast.makeText(CreateQuery.this, "Query successfully added", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(CreateQuery.this,"Please select one of the boxes",Toast.LENGTH_SHORT).show();
        }

    }
}