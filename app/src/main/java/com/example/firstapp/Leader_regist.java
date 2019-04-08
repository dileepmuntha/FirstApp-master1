package com.example.firstapp;
import android.Manifest;
        import android.app.AlertDialog;
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
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URI;
        import java.nio.InvalidMarkException;
        import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leader_regist extends AppCompatActivity {
    ImageView mImageview, image;
    private Uri uri;
    Button mupload, register;
    FirebaseDatabase database;
    private StorageReference imagePath;
    EditText email, password, name, confirmpassword, aadhar_no, phone_no,pincode;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    String imagPath;

    static SharedPreferences pref;
    static SharedPreferences.Editor editor;

    List<Database> registerdata;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_regist);

        database = FirebaseDatabase.getInstance();

        myRef = database.getReference("Registration");
        imagePath = FirebaseStorage.getInstance().getReference();
        pref = getSharedPreferences("MyPref", Context.MODE_PRIVATE); // 0 - for private mode
        editor = pref.edit();

        register = findViewById(R.id.register);
        name = findViewById(R.id.a1);
        email = findViewById(R.id.a2);
        password = findViewById(R.id.a3);
        confirmpassword = findViewById(R.id.a4);
        aadhar_no = findViewById(R.id.a5);
        phone_no = findViewById(R.id.a6);
       pincode= findViewById(R.id.a7);


        mAuth = FirebaseAuth.getInstance();

        name.addTextChangedListener(loginTextwatcher);
        email.addTextChangedListener(loginTextwatcher);
        password.addTextChangedListener(loginTextwatcher);
        confirmpassword.addTextChangedListener(loginTextwatcher);
        aadhar_no.addTextChangedListener(loginTextwatcher);
        phone_no.addTextChangedListener(loginTextwatcher);
        pincode.addTextChangedListener(loginTextwatcher);
//      mImageview.addOnAttachStateChangeListener((View.OnAttachStateChangeListener) loginTextwatcher);

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

    private TextWatcher loginTextwatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String nameInput = name.getText().toString().trim();
            String emailInput = email.getText().toString().trim();
            String passwordInput = password.getText().toString().trim();
            String uri = mImageview.toString().trim();

            String confirmpwdInput = confirmpassword.getText().toString().trim();
            String aadharInput = aadhar_no.getText().toString().trim();
            String phoneInput = phone_no.getText().toString().trim();
            String pincodeInput = pincode.getText().toString().trim();
            register.setEnabled(!nameInput.isEmpty() && !uri.isEmpty() && !emailInput.isEmpty() && !passwordInput.isEmpty() && !confirmpwdInput.isEmpty() && !aadharInput.isEmpty() && !phoneInput.isEmpty()&&!pincodeInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

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

    public void details(View view) {



        editor.putString("Name", name.getText().toString()); // Storing string
        editor.putString("Email", email.getText().toString()); // Storing string
        editor.putString("Password", password.getText().toString()); // Storing string
        editor.putString("Confimpassword", confirmpassword.getText().toString()); // Storing string
        editor.putString("Documents", mImageview.toString()); // Storing string
        editor.putString("Aadhaar", aadhar_no.getText().toString()); // Storing string
        editor.putString("Phonenumber", phone_no.getText().toString());
        editor.putString("Pincode", pincode.getText().toString());
        editor.commit(); // commit changes

        registerdata = new ArrayList<>();

        String mname = name.getText().toString();
        String memail = email.getText().toString();
        String mpassword = password.getText().toString();
        String mconfirmpassword = confirmpassword.getText().toString();
        String maadhar = aadhar_no.getText().toString();
        String mphoneno = phone_no.getText().toString();
        String mpincode= pincode.getText().toString();

        if (uri == null) {
            Toast.makeText(this, "Please select image", Toast.LENGTH_SHORT).show();
        }

       /* final Database database = new Database(mname, memail, mpassword, mconfirmpassword, maadhar, mphoneno,mpincode);

        registerdata.add(database);
        // whenever data at this location is updated.
        // myRef.setValue("Hellowfhjkh lkdfljds");
        final FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database1.getReference("Registration");

        myRef.child(mpincode).setValue(database);

        Toast.makeText(Leader_regist.this, "Details saved successfully", Toast.LENGTH_LONG).show();

*/
        uploadFile(uri);


        if (pincode.getText().toString().length() != 6) {

            pincode.setError("It's not a Valid Pincode");


        } else if (aadhar_no.getText().toString().length() != 12) {

            aadhar_no.setError("It's not a Valid Aadhaar");


        } else if (phone_no.getText().toString().length() != 10) {

            phone_no.setError("It's not a Valid Mobile No");
        } else if (password.getText().toString().length() != 8) {

            password.setError("Enter 8 Digits Password");

        } else if (confirmpassword.getText().toString().length() != 8) {
            confirmpassword.setError("Enter 8 Digits Password");
        } else if (!password.getText().toString().equals(confirmpassword.getText().toString())) {

            confirmpassword.setError("Miss match PASSWORD");
        } else {
            mname = name.getText().toString();
            memail = email.getText().toString();
            mpassword = password.getText().toString();
            mconfirmpassword = confirmpassword.getText().toString();
            maadhar = aadhar_no.getText().toString();
            mphoneno = phone_no.getText().toString();
            mpincode= pincode.getText().toString();

            Map<String, Object> map = new HashMap<>();
            map.put("Name", mname);
            map.put("Email", memail);
            map.put("Password", mpassword);
            map.put("Confirmpassword", mconfirmpassword);
            map.put("Aadhar", maadhar);
            map.put("Mobileno", mphoneno);
            map.put("Pincode", mpincode);
            map.put("image_url", uri.toString());

            myRef.child(mpincode).push().setValue(map);
            // Gmail.getText().clear();
            //  password.getText().clear();
            //  confirmpassword.getText().clear();
            // adharnumber.getText().clear();
            //  mobileno.getText().clear();

            mAuth.createUserWithEmailAndPassword(memail, mpassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            if (task.isSuccessful()) {
                                Toast.makeText(Leader_regist.this, "Register successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Leader_regist.this, leaderlogin.class);
                                startActivity(intent);



                            } else {
                                Toast.makeText(Leader_regist.this, "Register unsuccessful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Leader_regist.this, leaderlogin.class);
                                startActivity(intent);

                            }
                        }
                    });
            Intent intent = new Intent(Leader_regist.this, leaderlogin.class);
            startActivity(intent);
            /* email.getText().clear();
               password.getText().clear();
               confirmpassword.getText().clear();
               name.getText().clear();
               phone_no.getText().clear();
               aadhar_no.getText().clear();
               mImageview.setImageResource(R.drawable.no_image);*/


        }
    }

    private void uploadFile(Uri uri) {

        imagePath = FirebaseStorage.getInstance().getReference()
                .child("Documents").child(uri.getLastPathSegment());

        imagePath.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        imagePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Log.d("ore", "onSuccess: uri= " + uri.toString());
                                imagPath = uri.toString();
                                myRef = FirebaseDatabase.getInstance().getReference("Registration");

                                Map<String, Object> map = new HashMap<>();

                                Log.d("imgg", "" + uri.toString());
                               // map.put("image_url", uri.toString());
                                myRef.child(String.valueOf(image)).setValue(map);

                            }
                        });
                        /*  mProgress.dismiss();*/
                        Toast.makeText(Leader_regist.this, "Success...", Toast.LENGTH_SHORT).show();
                        // taskSnapshot.getMetadata().getReference().getDownloadUrl();
                        //imagPath = path;
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Leader_regist.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        });
    }
}

