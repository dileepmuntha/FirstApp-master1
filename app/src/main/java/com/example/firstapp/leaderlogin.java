package com.example.firstapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class leaderlogin extends AppCompatActivity {
    EditText username, password;
    Button login;
    private FirebaseAuth mAuth;
   /* static SharedPreferences pref;
    static  SharedPreferences.Editor editor;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderlogin);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login=findViewById(R.id.login);
        mAuth = FirebaseAuth.getInstance();
        username.addTextChangedListener(loginTextwatcher);
        password.addTextChangedListener(loginTextwatcher);
       /* pref = getSharedPreferences("MyPref", Context.MODE_PRIVATE); // 0 - for private mode
        editor = pref.edit();

        String email= pref.getString("Email","0");
        String password1= pref.getString("Password","0");
        username.setText(email);
        password.setText(password1);
*/
    }
    private TextWatcher loginTextwatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String usernameInput= username.getText().toString().trim();
            String passwordInput=password.getText().toString().trim();
            login.setEnabled(!usernameInput.isEmpty() && !passwordInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void gotoregister(View view) {
        Intent intent = new Intent(leaderlogin.this, Leader_regist.class);
        startActivity(intent);
    }

    public void login(View view) {
        String emai = username.getText().toString();
        String pwd = password.getText().toString();
        mAuth.signInWithEmailAndPassword(emai, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(leaderlogin.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(leaderlogin.this,LeaderProfile.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(leaderlogin.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    public void forget(View view) {
        Intent intent=new Intent(leaderlogin.this,ForgetPassword.class);
        startActivity(intent);
    }
}
