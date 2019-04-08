package com.example.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.StateSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChooseArea extends AppCompatActivity {
    private Spinner state_spinner, districtsspinner, dtt;
    private Button save;
    EditText pin;
    private DatabaseReference myRef;
    FirebaseDatabase database;
    String[] states,districts,ctrareas,kdpareas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_area);
        state_spinner = findViewById(R.id.state_spinner);
        districtsspinner = findViewById(R.id.districtsspinner);
        dtt = findViewById(R.id.dtt);
        save = findViewById(R.id.butttonnnn);
        states = getResources().getStringArray(R.array.states);
        districts = getResources().getStringArray(R.array.districts);
        ctrareas= getResources().getStringArray(R.array. chittoor_areas);
        kdpareas = getResources().getStringArray(R.array. kadapa_areas);
        pin = findViewById(R.id.pincode);
        //   myRef = database.getReference("Pincode");
        myRef = FirebaseDatabase.getInstance().getReference("Pincode");
        state_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String spinnerValue = state_spinner.getSelectedItem().toString();
                if (spinnerValue.equals("chittor_areas")) {
                 //   Adapterl adapter=new Adapterl(ChooseArea.this,ctrareas);
                    final ArrayAdapter<String> ctrar= new ArrayAdapter<String>(ChooseArea.this,android.R.layout.simple_dropdown_item_1line,ctrareas );

                    districtsspinner.setAdapter(ctrar);
                }else if (spinnerValue.equals("kadapa_areas")){
                 //   Adapterl adapter=new Adapterl(ChooseArea.this,kdpareas);
                    final ArrayAdapter<String> kdpar= new ArrayAdapter<String>(ChooseArea.this,android.R.layout.simple_dropdown_item_1line,kdpareas);

                    districtsspinner.setAdapter(kdpar);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        state_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
            updatebutton();

                /*Toast.makeText(parent.getContext(), "Selected" + state_spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();*/
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        districtsspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updatebutton();
                /* Toast.makeText(parent.getContext(), "Selected" + districtsspinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        dtt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                updatebutton();


                /*Toast.makeText(parent.getContext(), "Selected" + dtt.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = pin.getText().toString();
                Map<String, Object> map = new HashMap<>();
                map.put("Pincode", content);
               myRef.child(myRef.push().getKey()).setValue(map);

                Toast.makeText(ChooseArea.this, "ALL VALUES SELECTED", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ChooseArea.this, Profile.class);
                intent.putExtra("pincode_key",content);
                startActivity(intent);

            }
        });


    }


    private void updatebutton() {
        String state = state_spinner.getSelectedItem().toString();
        String district = districtsspinner.getSelectedItem().toString();
        String area = dtt.getSelectedItem().toString();
        String pincodeInput = pin.getText().toString().trim();

        if (!state.equals("Select State") && !district.equals("Select District") && !area.equals("Select Area")) {
            save.setEnabled(true);
        } else {
            save.setEnabled(false);
        }
    }
}



