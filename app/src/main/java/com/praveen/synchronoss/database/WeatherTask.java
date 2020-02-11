package com.praveen.synchronoss.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class WeatherTask implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "currentLocation")
    private String currentLocation;

    @ColumnInfo(name = "placeId")
    private String placeId;

    @ColumnInfo(name = "timeZone")
    private String timeZone;

    @ColumnInfo(name = "sunraiseTime")
    private String sunraiseTime;

    @ColumnInfo(name = "sunsetTime")
    private String sunsetTime;

    @ColumnInfo(name = "temperature")
    private String temperature;

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getSunraiseTime() {
        return sunraiseTime;
    }

    public void setSunraiseTime(String sunraiseTime) {
        this.sunraiseTime = sunraiseTime;
    }

    public String getSunsetTime() {
        return sunsetTime;
    }

    public void setSunsetTime(String sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
