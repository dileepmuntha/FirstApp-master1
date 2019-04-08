package com.example.firstapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {
    private EditText passwordEmail;
    private Button resetPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        passwordEmail=findViewById(R.id.recovery);
        resetPassword=findViewById(R.id.send);
        firebaseAuth=FirebaseAuth.getInstance();
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail=passwordEmail.getText().toString().trim();
                if (useremail.equals("")){
                    Toast.makeText(ForgetPassword.this,"please enter your registered email_id",Toast.LENGTH_LONG).show();
                }else {
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(ForgetPassword.this,"password reset email sent",Toast.LENGTH_LONG).show();
                                finish();
                                startActivity(new Intent(ForgetPassword.this,leaderlogin.class));
                            }else {
                                Toast.makeText(ForgetPassword.this,"Error in sending password reset email",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
}






