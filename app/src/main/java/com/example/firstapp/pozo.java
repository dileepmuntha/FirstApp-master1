package com.example.firstapp;

public class pozo  {
    String Description,image_url;

    public pozo(String description, String image_url) {
        Description = description;
        this.image_url = image_url;
    }
    public pozo() {

    }



    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
