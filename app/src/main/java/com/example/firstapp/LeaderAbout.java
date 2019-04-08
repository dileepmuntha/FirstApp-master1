package com.example.firstapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LeaderAbout extends AppCompatActivity {
    static SharedPreferences pref;
    static SharedPreferences.Editor editor;
    TextView name,email,aadhar,phone,pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_about);
        pref = getSharedPreferences("MyPref", Context.MODE_PRIVATE); // 0 - for private mode
        editor = pref.edit();
        name = findViewById(R.id.lname);
        email= findViewById(R.id.lemail);
        aadhar= findViewById(R.id.laadhar);
        phone = findViewById(R.id.lphone);
        pin=findViewById(R.id.lpincode);
        String namel = pref.getString("Name", "0");
        name.setText(namel);
        String emaill = pref.getString("Email", "0");
        email.setText(emaill);
        String aadharl= pref.getString("Aadhaar", "0");
        aadhar.setText(aadharl);
        String phonel= pref.getString("Phonenumber", "0");
        phone.setText(phonel);
        String pinl= pref.getString("Pincode", "0");
        pin.setText(pinl);


    }
}
