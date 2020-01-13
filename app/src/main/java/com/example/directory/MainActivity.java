package com.example.directory;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import android.app.Activity;
import android.os.Bundle;
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
    EditText Username,Email, PhoneNumber, Password, ConfirmPassword;
    Button btnCreate;
    TextView textViewSignIn;
    FirebaseAuth mFirebaseAuth;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        Username= (EditText)getView().findViewById(R.id.Username);
        Email =  (EditText)getView().findViewById(R.id.Email);
        PhoneNumber =  (EditText)getView().findViewById(R.id.PhoneNumber);
        Password =  (EditText)getView().findViewById(R.id.Password);
        ConfirmPassword =  (EditText)getView().findViewById(R.id.ConfirmPassword);
        btnCreate =  (EditText)getView().findViewById(R.id.btnCreate);


        btnCreate.setOnClickListener(new View.onClickListener(){

            public void onClick(View v){
                String username= Username.getText().toString().trim();
                String emailadd = Email.getText().toString().trim();
                String phone = PhoneNumber.getText().toString().trim();
                String pswd = Password.getText().toString().trim();
                String cnfrmpswd = ConfirmPassword.getText().toString().trim();

                if(username.isEmpty()){
                   Toast.makeText(getApplicationContext(), "Please enter your username!", Toast.LENGTH_SHORT).show();
                   return;

                }
                if (emailadd.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter your email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phone.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter your phone number!", Toast.LENGTH_SHORT).show();
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
                if (cnfrmpswd.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter your confirm password!", Toast.LENGTH_SHORT).show();
                    return;
                    }
                if(username.isEmpty() && emailadd.isEmpty() && phone.isEmpty() && pswd.isEmpty() && cnfrmpswd.isEmpty() ){
                    Toast.makeText(getApplicationContext(), "Fields are empty!", Toast.LENGTH_SHORT).show();
                    return;
                    }

                auth.CreateUserwithEmailandPassword(Email, Password)
                        .addOnCompleteListener(MainActivity.this, new onCompleteListener<AuthResult>(){

                            public void onComplete(@NonNull Task<AuthResult> task){
                                Toast.makeText(MainActivity.this, "createUserwithEmail:onComplete" + task.isSuccessful(),Toast.LENGTH_SHORT).show();
                                if(!task.isSucessful()){
                                    Toast.makeText(MainActivity.this,"Failed to create account." + task.getException(),Toast.LENGTH_SHORT).show();

                                }else{
                                    startActivity(new intent(MainActivity.this,MainActivity.class));
                                    finish();
                                }
                            }
                        });

                    }
                });
    }

}
