package com.example.emran;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText email,password;
    Button login;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.adminEmailEditTextId);
        password = findViewById(R.id.adminPassEditTextId);
        login = findViewById(R.id.adminLoginButtonId);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();


        if (firebaseUser != null){
            Intent intent = new Intent(LoginActivity.this,DatabaseActivity.class);
            startActivity(intent);
            finish();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = email.getText().toString();
                String s2 = password.getText().toString();
                if (!TextUtils.isEmpty(s1) && !TextUtils.isEmpty(s2)){
                    signin();
                }else {
                    Toast.makeText(LoginActivity.this, "Please Enter Valid Email and Password", Toast.LENGTH_SHORT).show();
                    email.setError("Enter Valid Email");
                    password.setError("Enter valid Password");
                }
            }
        });

    }

    private void signin() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        String e1 = email.getText().toString();
        String e2 = password.getText().toString();
        mAuth.signInWithEmailAndPassword(e1, e2)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent = new Intent(getApplicationContext(),DatabaseActivity.class);
                            startActivity(intent);
                            finish();
                            progressDialog.dismiss();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Email and Password does not match.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }
}