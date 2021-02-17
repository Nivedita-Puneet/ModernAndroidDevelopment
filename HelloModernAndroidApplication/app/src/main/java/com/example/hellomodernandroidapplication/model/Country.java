package com.example.hellomodernandroidapplication.model;

import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("name")
    String name;
    @SerializedName("capital")
    String capital;
    @SerializedName("flagPNG")
    String flag;

    public Country(String name, String capital, String flag) {
        this.name = name;
        this.capital = capital;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getFlag() {
        return flag;
    }

}
