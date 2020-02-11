package com.praveen.synchronoss.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WeatherDao {

    @Query("SELECT * FROM WeatherTask")
    List<WeatherTask> getAll();

    @Insert
    void insert(WeatherTask weatherTask);
}
