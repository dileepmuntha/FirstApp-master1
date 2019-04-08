package com.example.firstapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PeopleAbout extends AppCompatActivity {
    static SharedPreferences pref;
    static SharedPreferences.Editor editor;
    TextView name,email,aadhar,phone,pin;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    List<pozol> data=new ArrayList<>();
    String pincode;

    DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_about);
        recyclerView=findViewById(R.id.recyclerview1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Write a message to the database

        pincode = getIntent().getStringExtra("pincode_key");

        if (!TextUtils.isEmpty(pincode)) {
            database = FirebaseDatabase.getInstance().getReference("Registration/"+pincode);

            final ArrayList<pozol> arrayList=new ArrayList<>();
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds:dataSnapshot.getChildren()){

                        String aadhar = ds.child("Aadhar").getValue(String.class);
                        String c_password = ds.child("Confirmpassword").getValue(String.class);
                        String email = ds.child("Email").getValue(String.class);
                        String mobile= ds.child("Mobileno").getValue(String.class);
                        String name= ds.child("Name").getValue(String.class);
                        String password = ds.child("Password").getValue(String.class);
                        String pincode= ds.child("Pincode").getValue(String.class);
                        String document= ds.child("image_url").getValue(String.class);

                        pozol pozoo=new pozol();

                        pozoo.setAadhar(aadhar);
                        pozoo.setConfirmpassword(c_password);
                        pozoo.setEmail(email);
                        pozoo.setMobileno(mobile);
                        pozoo.setName(name);
                        pozoo.setPassword(password);
                        pozoo.setPincode(pincode);
                        pozoo.setImage_url(document);

                        arrayList.add(pozoo);
                    }
                    Log.i("size",""+arrayList.size());
                    Adapterl adapterl=new Adapterl(PeopleAbout.this,arrayList);
                    recyclerView.setAdapter(adapterl);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        Log.i("Pincode:",pincode);



        /*DatabaseReference myRef = database.getReference("message");

     //   Adapterl myadapter=new Adapterl(PeopleAbout.this,data);
      //  RecyclerView recyclerView=findViewById(R.id.recyclerview);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setAdapter(myadapter);

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
        recyclerView=findViewById(R.id.recyclerview);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {

                    String namel =dataSnapshot1.child("Name").getValue(String.class);
                    String emaill=dataSnapshot1.child("Email").getValue(String.class);

                    String passwordl =dataSnapshot1.child("Password").getValue(String.class);
                    String confirmpasswordl=dataSnapshot1.child("Confirmpassword").getValue(String.class);

                    String aadharl=dataSnapshot1.child("Aadhar").getValue(String.class);
                    String docs=dataSnapshot1.child("image_url").getValue(String.class);
                    String phonel=dataSnapshot1.child("Mobileno").getValue(String.class);
                    String pincodel =dataSnapshot1.child("Pincode").getValue(String.class);
                    pozol pozoo = new pozol(namel,emaill,passwordl,confirmpasswordl,docs,aadharl,phonel,pincodel);
                    data.add(pozoo);
                }
                layoutManager = new LinearLayoutManager(PeopleAbout.this);
                Adapterl myadapter=new Adapterl(PeopleAbout.this,data);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(myadapter);
                Toast.makeText(PeopleAbout.this, ""+data.size(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.i("Error",databaseError.getMessage());
            }


        });*/

    }
}
