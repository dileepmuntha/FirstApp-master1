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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CreatePost extends AppCompatActivity {
    ImageView mImageview,image;
    Button mupload;
    EditText desc;
    private Uri uri;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    FirebaseDatabase database;
    private StorageReference imagePath;
    String imagPath;
    List<Database> registerdata;

    static SharedPreferences pref;
    static SharedPreferences.Editor editor;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        database = FirebaseDatabase.getInstance();

        myRef = database.getReference("Posts");
        imagePath = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        desc=findViewById(R.id.description);
        mImageview = findViewById(R.id.image_view);
        mupload = findViewById(R.id.image_button);


        mupload.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_CODE);


                    } else {
                        pickImageFromGallery();

                    }
                } else {

                    pickImageFromGallery();
                }

            }
        });
    }


    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_CODE: {
                if ((grantResults.length > 0) && (grantResults[0]) == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery();
                } else {
                    Toast.makeText(this, "PERMISSION DENIED..!", Toast.LENGTH_LONG).show();
                }
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((resultCode == RESULT_OK) && (requestCode == IMAGE_PICK_CODE)) {
            mImageview.setImageURI(data.getData());
            uri = data.getData();
        }
    }

    public void posts(View view) {
        pref = getSharedPreferences("MyPref", Context.MODE_PRIVATE); // 0 - for private mode
        editor = pref.edit();
        editor.putString("Documents", mImageview.toString());
        editor.putString("Content", desc.toString());

        editor.commit(); // commit changes

        registerdata = new ArrayList<>();

        if (imagPath == null) {
            Toast.makeText(this, "Posted succeessfully", Toast.LENGTH_SHORT).show();
        }

        uploadFile(uri);


    }
    private void uploadFile(Uri uri) {

        imagePath = FirebaseStorage.getInstance().getReference()
                .child("Posts").child(uri.getLastPathSegment());

        imagePath.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        imagePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Log.d("ore", "onSuccess: uri= " + uri.toString());
                                imagPath = uri.toString();
                                myRef = FirebaseDatabase.getInstance().getReference("Posts");

                                String content = desc.getText().toString();
                                Map<String, Object> map = new HashMap<>();
                                map.put("Description", content);
                                map.put("image_url", uri.toString());
                                myRef.child(myRef.push().getKey()).setValue(map);

                                Log.d("imgg", "" + uri.toString());
                               // map.put("image_url", uri.toString());


                            }
                        });
                        /*  mProgress.dismiss();*/
                        Toast.makeText(CreatePost.this, "Success...", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(CreatePost.this,LeaderProfile.class);
                        startActivity(intent);
                        // taskSnapshot.getMetadata().getReference().getDownloadUrl();
                        //imagPath = path;
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CreatePost.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
