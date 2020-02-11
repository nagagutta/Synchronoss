package com.praveen.synchronoss.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main extends BaseObservable {

    @SerializedName("temp")
    @Expose
    private String temp;

    @SerializedName("feels_like")
    @Expose
    private String feels_like;

    @SerializedName("temp_min")
    @Expose
    private String temp_min;

    @SerializedName("temp_max")
    @Expose
    private String temp_max;

    @SerializedName("pressure")
    @Expose
    private String pressure;

    @SerializedName("humidity")
    @Expose
    private String humidity;

    @Bindable
    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    @Bindable
    public String getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(String feels_like) {
        this.feels_like = feels_like;
    }

    @Bindable
    public String getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(String temp_min) {
        this.temp_min = temp_min;
    }

    @Bindable
    public String getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(String temp_max) {
        this.temp_max = temp_max;
    }

    @Bindable
    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    @Bindable
    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}
