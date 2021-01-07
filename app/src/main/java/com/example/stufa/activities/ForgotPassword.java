package com.example.stufa.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.stufa.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class ForgotPassword extends AppCompatActivity {

    EditText etResetEmail;
    Button btnSubmit;

    View progressBarLayout, contentLayout;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.reset_password));
        actionBar.setIcon(R.drawable.reset_password);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        etResetEmail = findViewById(R.id.etResetEmail);
        btnSubmit = findViewById(R.id.btnSubmit);

        progressBarLayout = findViewById(R.id.progressBarLayout);
        contentLayout = findViewById(R.id.contentLayout);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etResetEmail.getText().toString().isEmpty())
                {
                    Toast.makeText(ForgotPassword.this, getString(R.string.please_enter_your_email_address), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    progressBarLayout.setVisibility(View.VISIBLE);
                    contentLayout.setVisibility(View.GONE);
                    firebaseAuth.sendPasswordResetEmail(etResetEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {


                            if(task.isSuccessful())
                            {
                                progressBarLayout.setVisibility(View.GONE);
                                contentLayout.setVisibility(View.VISIBLE);
                                Toast.makeText(ForgotPassword.this, getString
                                                (R.string.password_successfully_sent_to_your_email_address),
                                        Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                progressBarLayout.setVisibility(View.GONE);
                                contentLayout.setVisibility(View.VISIBLE);
                                Toast.makeText(ForgotPassword.this, getString
                                                (R.string.error) + task.getException().getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.forgot_password, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.back) {
            startActivity(new Intent(getApplicationContext(), Login.class));
        }

        return super.onOptionsItemSelected(item);
    }
}