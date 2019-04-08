package com.example.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Feedback extends AppCompatActivity {
    FirebaseDatabase database;
    private DatabaseReference myRef;
    EditText fbc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        database = FirebaseDatabase.getInstance();
        fbc=findViewById(R.id.feedback);
        myRef = database.getReference("Feedback");
        myRef = FirebaseDatabase.getInstance().getReference("Feedback");



    }
    public void send(View view) {
        String content = fbc.getText().toString();
        Map<String, Object> map = new HashMap<>();
        map.put("Feedback", content);
        myRef.child(myRef.push().getKey()).setValue(map);
        Toast.makeText(Feedback.this, "Thanks for your feedback", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(Feedback.this,AboutApp.class);
        startActivity(intent);

    }
}
