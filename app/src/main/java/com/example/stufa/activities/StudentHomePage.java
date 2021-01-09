package com.example.stufa.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stufa.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class StudentHomePage extends AppCompatActivity {

    Button btnLogout, btnAllowanceRelatedQuery, btnBooking, btnGeneralQuery,
           btnFinancialStatement, btnFinancialClearance, btnFillForm;
    TextView tvGreeting, tvFullName, tvStudentNumber, tvEmail, tvCourse, tvBookingPercentage;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    String userID;
    FragmentManager fragmentManager;
    Fragment listFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home_page);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.student_home));
        actionBar.setIcon(R.drawable.person);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

//        btnLogout = findViewById(R.id.btnLogout);
        tvGreeting = findViewById(R.id.tvGreeting);
        tvFullName = findViewById(R.id.tvFullName);
//        tvStudentNumber = findViewById(R.id.tvStudentNumber);
//        tvEmail = findViewById(R.id.tvEmail);
//        tvCourse = findViewById(R.id.tvCourse);
        btnAllowanceRelatedQuery = findViewById(R.id.btnAllowanceRelatedQuery);
        btnBooking = findViewById(R.id.btnBooking);
//        btnGeneralQuery = findViewById(R.id.btnGeneralQuery);
        btnFinancialStatement = findViewById(R.id.btnFinancialStatement);
        btnFinancialClearance = findViewById(R.id.btnFinancialClearance);
        btnFillForm = findViewById(R.id.btnFillForm);
        tvBookingPercentage = findViewById(R.id.tvBookingPercentage);

        fragmentManager = getSupportFragmentManager();

        listFrag = fragmentManager.findFragmentById(R.id.announcements_list_frag);

        fragmentManager.beginTransaction().show(listFrag).commit();

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        userID = firebaseAuth.getCurrentUser().getUid();

        //Reads the data entered when the user registered just to check if we can read back the data
        DocumentReference documentReference = firestore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                assert value != null;
                tvFullName.setText(String.format("%s%s%s", value.getString("name"), " ", value.getString("surname")));

//                tvEmail.setText(String.format("%s%s",  "Email Address: ", value.getString("email")));
//
//                tvCourse.setText(String.format("%s%s",  "Campus: ", value.getString("campus")));
//
//                tvStudentNumber.setText(String.format("%s%s",  "Student Number: ", value.getString("studentNumber")));
            }
        });

        btnAllowanceRelatedQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentHomePage.this, CreateQuery.class);
                startActivity(intent);
            }
        });

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentHomePage.this, CreateBooking.class);
                startActivity(intent);
            }
        });

//        btnGeneralQuery.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(StudentHomePage.this, CreateQuery.class);
//                startActivity(intent);
//            }
//        });

        btnFinancialStatement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentHomePage.this, CreateRequest.class);
                startActivity(intent);
            }
        });

        btnFinancialClearance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentHomePage.this, CreateRequest.class);
                startActivity(intent);
            }
        });

        btnFillForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentHomePage.this, FillForm.class);
                startActivity(intent);
            }
        });

    }

//    //Logs out the use and sends them to the Login Activity
//    public void Logout(View view) {
//        FirebaseAuth.getInstance().signOut();//used for logging out the user
//        startActivity(new Intent(getApplicationContext(), Login.class));
//        finish();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.home, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.refresh)//Refreshes the bookings percentage
        {
            Toast.makeText(this, "Refreshing Bookings...", Toast.LENGTH_LONG).show();

        }
        if (item.getItemId() == R.id.logout)//Logs out the use and sends them to the Login Activity
        {

            Toast.makeText(this, "Logging user out...", Toast.LENGTH_LONG).show();

            FirebaseAuth.getInstance().signOut();//used for logging out the user
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}