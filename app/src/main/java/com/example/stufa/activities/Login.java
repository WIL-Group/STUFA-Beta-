package com.example.stufa.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stufa.R;
import com.example.stufa.app_utilities.Utilities;
import com.example.stufa.data_models.Announcement;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Login extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin;
    TextView tvCreateAccount, tvForgotPassword;
    View progressBarLayout, contentLayout;

    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    private String date, id;
    Boolean viewed = false;
    DatabaseReference announcementRef;
    ArrayList<Announcement> announcements;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.login));
        actionBar.setIcon(R.drawable.lock);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvCreateAccount = findViewById(R.id.tvCreateAccount);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);



        progressBarLayout = findViewById(R.id.progressBarLayout);
        contentLayout = findViewById(R.id.contentLayout);

        progressBar = findViewById(R.id.progressBar);
        firebaseAuth = FirebaseAuth.getInstance();

        announcementRef = FirebaseDatabase.getInstance().getReference().child("Announcements");
        announcements = new ArrayList<>();


        btnLogin.setOnClickListener(v -> {

            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if(etEmail.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty())
            {
                Toast.makeText(Login.this, "Please enter all fields!", Toast.LENGTH_SHORT).show();
                if(TextUtils.isEmpty(email))
                {
                    etEmail.setError("Email address is Required");
                }
                else if(TextUtils.isEmpty(password))
                {
                    etPassword.setError("Password is Required");
                }
                else if(password.length() < 6)
                {
                    etPassword.setError("Password has to be 6 characters or above");
                }

            }
            else
            {
                contentLayout.setVisibility(View.GONE);
                progressBarLayout.setVisibility(View.VISIBLE);
                //progressBar.setVisibility(View.VISIBLE);

                // authenticating the user

                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Login.this, "Successfully Logged In!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), StudentHomePage.class));

                        }
                        else
                        {
                            Toast.makeText(Login.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBarLayout.setVisibility(View.GONE);
                            contentLayout.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }

        });

        tvCreateAccount.setOnClickListener(v -> {

            Intent intent = new Intent(Login.this, CreateAccount.class);
            startActivity(intent);

        });

        tvForgotPassword.setOnClickListener(v -> {

            Intent intent = new Intent(Login.this, ForgotPassword.class);
            startActivity(intent);

        });

       /* date = new SimpleDateFormat("dd MM, yyyy", Locale.getDefault()).format(new Date());

        Announcement announcement = new Announcement(id, "News", "Ho hobe Satafrika", date, viewed);

        announcementRef.push().setValue(announcement);
        for(int i = 0; i < announcements.size(); i++)
        {
            id = i + announcement.getMessage().charAt(2)+ "";
            announcement = announcements.get(i);
            announcement.setaId(id);
        }*/

    }

}
