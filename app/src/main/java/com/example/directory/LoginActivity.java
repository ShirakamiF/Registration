package com.example.directory;


import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends Activity {
    EditText Email, Password;
    Button btnLogin;
    FirebaseAuth auth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        setContentView(R.layout.activity_login);


        Email = (EditText) getView().findViewById(R.id.Email);
        Password = (EditText) getView().findViewById(R.id.Password);

        auth = FirebaseAuth.getInstance();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String emailadd = Email.getText().toString();
                final String password = Password.getText().toString();

                if (emailadd.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter your email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pswd.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter your password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pswd.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.CreateUserwithEmailandPassword(Email, Password)
                        .addOnCompleteListener(LoginActivity.this, new onCompleteListener<AuthResult>() {

                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(LoginActivity.this, "createUserwithEmail:onComplete" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                if (!task.isSucessful()) {
                                    Toast.makeText(LoginActivity.this, "Failed to create account." + task.getException(), Toast.LENGTH_SHORT).show();

                                } else {
                                    startActivity(new intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });

            }
        });
    }
}

