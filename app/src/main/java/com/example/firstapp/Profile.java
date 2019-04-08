package com.example.firstapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    TextView lname;
    static SharedPreferences pref;
    static SharedPreferences.Editor editor;
    String pincode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        lname=findViewById(R.id.lename);

        pincode = getIntent().getStringExtra("pincode_key");
        pref = getSharedPreferences("MyPref", Context.MODE_PRIVATE); // 0 - for private mode
        editor = pref.edit();
        String name = pref.getString("Name", "0");
        lname.setText(name);

    }
    public void postl(View view) {
        Intent intent=new Intent(Profile.this,ViewPosts.class);
        startActivity(intent);
    }
    public void aboutl(View view) {
        Intent intent=new Intent(Profile.this,PeopleAbout.class);
        intent.putExtra("pincode_key",pincode);
        startActivity(intent);

    }
}
