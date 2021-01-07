package com.example.stufa;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class CreateQuery extends AppCompatActivity implements QueryAdapter.ItemClicked  {

    CheckBox cbBookAllowance, cbMealAllowance, cbAccommodationOrTransportAllowance;
    EditText etQueryMessage;
    Button btnSave, btnDelete, btnSubmit;

    RecyclerView recyclerView;
    private QueryAdapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    private ArrayList<Query> queries;
    Query query;
    String type, message;


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


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(cbBookAllowance.isChecked() || cbMealAllowance.isChecked() || cbAccommodationOrTransportAllowance.isChecked())
            {
                if(etQueryMessage.getText().toString().isEmpty())
                {
                    Toast.makeText(CreateQuery.this, "Please enter query message", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(!cbBookAllowance.getText().toString().isEmpty())
                    {
                        type = cbBookAllowance.getText().toString().trim();
                    }
                    else if(!cbMealAllowance.getText().toString().isEmpty())
                    {
                        type = cbMealAllowance.getText().toString().trim();
                    }
                    else if(!cbAccommodationOrTransportAllowance.getText().toString().isEmpty())
                    {
                        type = cbAccommodationOrTransportAllowance.getText().toString().trim();
                    }

                    message = etQueryMessage.getText().toString().trim();

                    query = new Query(type, message);
                    queries.add(query);

                    myAdapter = new QueryAdapter(queries,CreateQuery.this);
                    recyclerView.setAdapter(myAdapter);
                    Toast.makeText(CreateQuery.this, "Query successfully added", Toast.LENGTH_SHORT).show();
                }

            }

            }

        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(query == null)
                {
                    Toast.makeText(CreateQuery.this,"Please select a query to delete", Toast.LENGTH_SHORT);
                }
                else
                {
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
}