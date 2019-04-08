package com.example.firstapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Madapter extends RecyclerView.Adapter<Madapter.ViewHolder> {

    List<pozo> data;
    ViewPosts mainactivity;

    public Madapter(ViewPosts mainActivity, List<pozo> data) {


        this.data = data;
        this.mainactivity = mainActivity;
    }

    @NonNull
        @Override
        public ViewHolder onCreateViewHolder (@NonNull ViewGroup viewGroup,int i){

            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.post, viewGroup, false);
            ViewHolder viewHolder=new ViewHolder(view);
            return viewHolder;

        }

        @Override
        public void onBindViewHolder (@NonNull Madapter.ViewHolder viewHolder,int i){

            Picasso.with(mainactivity).load(data.get(i).getImage_url()).into(viewHolder.images);
            viewHolder.description.setText(data.get(i).getDescription());
        }

        @Override
        public int getItemCount () {
            return data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView images;
        TextView description;
            public ViewHolder(@NonNull View itemView)
            {
                super(itemView);
                images=itemView.findViewById(R.id.postimage);
                description =itemView.findViewById(R.id.postdesc);

            }
        }
    }


