package com.example.stufa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateAccount extends AppCompatActivity {

    EditText etName, etSurname, etEmail, etStudentNumber, etPassword, etConfirmPassword;
    Button btnSignUp;
    TextView tvLogin;
//    RadioGroup rgCampus;
//    RadioButton rbCampusResult;

    SwitchCompat switchPos;
    View progressBarLayout, contentLayout;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("CREATE NEW ACCOUNT");
        //actionBar.setIcon(R.drawable.lock);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        etName = findViewById(R.id.etName);
        etSurname = findViewById(R.id.etSurname);
        etEmail = findViewById(R.id.etEmail);
        etStudentNumber = findViewById(R.id.etStudentNumber);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvLogin = findViewById(R.id.tvLogin);

//        rgCampus = findViewById(R.id.rgCampus);
        switchPos = findViewById(R.id.switchPos);
        progressBarLayout = findViewById(R.id.progressBarLayout);
        contentLayout = findViewById(R.id.contentLayout);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();


        //tests if there is a user already in the firebase database
//        if(firebaseAuth.getCurrentUser() != null)
//        {
//            startActivity(new Intent(getApplicationContext(),MainActivity.class));
//            finish();
//        }


        switchPos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                //checks if the switch position state is true
//                if(isChecked)
//                {
//                    switchPos.setText(R.string.welkom_campus);
//
//                }
//                else
//                {
//                    switchPos.setText(R.string.bloemfontein_campus);
//                }
            }
        });


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                StringBuffer result = new StringBuffer();

                final String name = etName.getText().toString().trim();
                final String surname = etSurname.getText().toString().trim();
                final String email = etEmail.getText().toString().trim();
                final String studentNumber = etStudentNumber.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String confirmPassword = etConfirmPassword.getText().toString().trim();

//                int campus = rgCampus.getCheckedRadioButtonId();
//                rbCampusResult = findViewById(campus);
//
//                if(rbCampusResult != null)
//                {
//                    result.append("campus").append(rbCampusResult.getText().toString());
//                }

                if(TextUtils.isEmpty(name))
                {
                    etName.setError("Name address is Required");
                    return;
                }
                else if(TextUtils.isEmpty(surname))
                {
                    etSurname.setError("Surname address is Required");
                    return;
                }
                else if(TextUtils.isEmpty(email))
                {
                    etEmail.setError("Email address is Required");
                    return;
                }

//                else if(TextUtils.isEmpty(campus))
//                {
//                    etCampas.setError("Campus name is Required");
//                    return;
//                }

                else if(TextUtils.isEmpty(studentNumber))
                {
                    etStudentNumber.setError("Student number is Required");
                    return;
                }
                else if(TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword))
                {
                    etPassword.setError("Password is Required");
                    etConfirmPassword.setError("Password is Required");
                    return;
                }
                else if(password.length() < 6)
                {
                    etPassword.setError("Password has to be 6 characters or more");
                    return;
                }
                else if(!etConfirmPassword.getText().toString().equals(etPassword.getText().toString()))
                {
                    etConfirmPassword.setError("Passwords do not match");
                    return;
                }


                progressBarLayout.setVisibility(View.VISIBLE);
                contentLayout.setVisibility(View.GONE);

                //will now register the use into Firebase
                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(CreateAccount.this, "Successfully created!", Toast.LENGTH_SHORT).show();

                            userID = firebaseAuth.getCurrentUser().getUid();

                            DocumentReference documentReference = firestore.collection("users").document(userID);

                            Map<String, Object> user = new HashMap<>();


                            user.put("name", name);
                            user.put("surname", surname);
                            user.put("email", email);
                            user.put("studentNumber", studentNumber);
//                            user.put("campus", rbCampusResult);

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    Toast.makeText(CreateAccount.this, "Profile successfully created!", Toast.LENGTH_LONG).show();

                                }
                            });
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            CreateAccount.this.finish();
                        }
                        else //if the user already exists in the database an error message will show
                        {
                            Toast.makeText(CreateAccount.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            progressBarLayout.setVisibility(View.GONE);
                            startActivity(new Intent(getApplicationContext(),CreateAccount.class));
                        }
                    }
                });
            }
        });


        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateAccount.this, Login.class);
                startActivity(intent);
            }
        });

    }

}