package com.example.firstapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LeaderProfile extends AppCompatActivity {
    static SharedPreferences pref;
    static SharedPreferences.Editor editor;
    TextView lname;
    Button post;
    private Uri uri;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_profile);

        pref = getSharedPreferences("MyPref", Context.MODE_PRIVATE); // 0 - for private mode
        editor = pref.edit();
        lname = findViewById(R.id.name);


        String name = pref.getString("Name", "0");
        lname.setText(name);
        post = findViewById(R.id.createpost);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeaderProfile.this, CreatePost.class);
                startActivity(intent);
            }
        });

    }

    public void post(View view) {
        Intent intent =new Intent(LeaderProfile.this,ViewPosts.class);
        startActivity(intent);
    }

    public void about(View view) {
        Intent intent=new Intent(LeaderProfile.this,LeaderAbout.class);
        startActivity(intent);
    }
}



