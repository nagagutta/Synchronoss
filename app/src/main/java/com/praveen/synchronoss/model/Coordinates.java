package com.praveen.synchronoss.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coordinates extends BaseObservable {

    @SerializedName("lon")
    @Expose
    private String lon;

    @SerializedName("lat")
    @Expose
    private String lat;

    @Bindable
    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    @Bindable
    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
