package com.example.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void navigate(View view) {
        Intent intent = new Intent(MainActivity.this,leaderlogin.class);
        startActivity(intent);

    }

    public void publicnav(View view) {
        Intent intent = new Intent(MainActivity.this,PublicLogin.class);
        startActivity(intent);
    }



}
