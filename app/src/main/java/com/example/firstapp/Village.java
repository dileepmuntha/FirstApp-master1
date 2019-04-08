package com.example.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Village extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_village);
    }
    public void About(View view) {
        Intent intent=new Intent(Village.this,AboutApp.class);
        startActivity(intent);
    }


    public void Village(View view) {
        Intent intent =new Intent(Village.this,ChooseArea.class);
        startActivity(intent);
    }



}

