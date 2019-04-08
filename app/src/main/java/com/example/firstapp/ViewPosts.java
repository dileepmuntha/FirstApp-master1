package com.example.firstapp;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewPosts extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    List<pozo> data=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_posts);

        recyclerView=findViewById(R.id.recyclerview);

        myRef = FirebaseDatabase.getInstance().getReference("Posts");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {

                    String desc =dataSnapshot1.child("Description").getValue(String.class);
                    String url =dataSnapshot1.child("image_url").getValue(String.class);
                    pozo pozoo = new pozo(desc,url);
                    data.add(pozoo);
                }
                layoutManager = new LinearLayoutManager(ViewPosts.this);
                Madapter myadapter=new Madapter (ViewPosts.this,data);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(myadapter);
                Toast.makeText(ViewPosts.this, ""+data.size(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.i("Error",databaseError.getMessage());
            }


        });

        Toast.makeText(this, ""+data.size(), Toast.LENGTH_SHORT).show();

    }
/*
    private class Picasso {
    }*/
}
