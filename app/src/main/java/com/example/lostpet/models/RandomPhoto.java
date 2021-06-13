package com.example.lostpet.models;

public class RandomPhoto{

    public String owner;
    public String url;

    public RandomPhoto(String owner, String url) {
        this.owner=owner;
        this.url = url;
    }


    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}