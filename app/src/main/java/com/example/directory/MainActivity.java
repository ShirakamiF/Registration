package com.example.directory;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;

public class MainActivity extends Activity {
    EditText Username, Email, PhoneNumber, Password, ConfirmPassword;
    Button btnCreate;
    TextView textViewSignIn;
    FirebaseAuth mFirebaseAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        Username = (EditText) findViewById(R.id.Username);
        Email = (EditText) findViewById(R.id.Email);
        PhoneNumber = (EditText) findViewById(R.id.PhoneNumber);
        Password = (EditText) findViewById(R.id.Password);
        ConfirmPassword = (EditText) findViewById(R.id.ConfirmPassword);
        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = Username.getText().toString().trim();
                String email = Email.getText().toString().trim();
                String phone = PhoneNumber.getText().toString().trim();
                String password = Password.getText().toString().trim();
                String confirmpassword = ConfirmPassword.getText().toString().trim();

                if (username.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter your username!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (email.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter your email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phone.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter your phone number!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter your password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (confirmpassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter your confirm password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (username.isEmpty() && email.isEmpty() && phone.isEmpty() && password.isEmpty() && confirmpassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fields are empty!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!(username.isEmpty() && email.isEmpty() && phone.isEmpty() && password.isEmpty() && confirmpassword.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Create Account Unsuccessful", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Error Occurred", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}



