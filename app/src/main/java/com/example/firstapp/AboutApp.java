package com.example.firstapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AboutApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
    }
    public void Share(View view) {
        Uri uri= Uri.parse("https://www.drive.google.com");
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
    public void feedback(View view) {
        Intent intent=new Intent(AboutApp.this,Feedback.class);
        startActivity(intent);
    }
    public void version(View view) {

        Intent intent=new Intent(AboutApp.this,Version.class);
        startActivity(intent);
    }

    public void privacy(View view) {
        Uri uri= Uri.parse("https://termsfeed.com/blog/privacy-policy-mobile-apps/#Privacy_Policy_for_Android_apps");
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
}

