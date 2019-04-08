package com.example.firstapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Adapterl extends RecyclerView.Adapter<Adapterl.ViewHolder> {
    ArrayList<pozol> data;
    PeopleAbout mainactivity;
    public Adapterl(PeopleAbout mainActivity, ArrayList<pozol> data) {


        this.data = data;
        this.mainactivity = mainActivity;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.aboutl, viewGroup, false);
        Adapterl.ViewHolder viewHolder=new Adapterl.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapterl.ViewHolder viewHolder, int i) {
        viewHolder.name.setText(data.get(i).getName());
        viewHolder.email.setText(data.get(i).getEmail());
        viewHolder.aadhar.setText(data.get(i).getAadhar());
        viewHolder.phone.setText(data.get(i).getMobileno());
        viewHolder.pincode.setText(data.get(i).getPincode());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,email,password,confirmpassword,aadhar,phone,pincode;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.lname);
            email=itemView.findViewById(R.id.lemail);
           /* password=itemView.findViewById(R.id.postimage);
            confirmpassword=itemView.findViewById(R.id.l);*/
            aadhar=itemView.findViewById(R.id.laadhar);
            phone=itemView.findViewById(R.id.lphone);
            pincode=itemView.findViewById(R.id.lpincode);

        }
    }
}
