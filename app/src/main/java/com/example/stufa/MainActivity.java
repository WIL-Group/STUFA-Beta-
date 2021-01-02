package com.example.stufa;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainActivity extends AppCompatActivity {

    Button btnLogout;
    TextView tvFullName, tvStudentNumber, tvEmail, tvCourse;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogout = findViewById(R.id.btnLogout);
        tvFullName = findViewById(R.id.tvFullName);
        tvStudentNumber = findViewById(R.id.tvStudentNumber);
        tvEmail = findViewById(R.id.tvEmail);
        tvCourse = findViewById(R.id.tvCourse);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        userID = firebaseAuth.getCurrentUser().getUid();

        //Reads the data entered when the user registered just to check if we can read back the data
        DocumentReference documentReference = firestore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                assert value != null;
                tvFullName.setText(String.format("%s%s%s%s", "Full Name: ", value.getString("name"), " ", value.getString("surname")));

                tvEmail.setText(String.format("%s%s",  "Email Address: ", value.getString("email")));

//                tvCourse.setText(String.format("%s%s",  "Campus: ", value.getString("campus")));
//                tvCourse.setText(value.getString("course"));

                tvStudentNumber.setText(String.format("%s%s",  "Student Number: ", value.getString("studentNumber")));
            }
        });

    }

    //Logs out the use and sends them to the Login Activity
    public void Logout(View view) {
        FirebaseAuth.getInstance().signOut();//used for logging out the user
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }

}