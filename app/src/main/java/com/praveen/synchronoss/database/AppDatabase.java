package com.praveen.synchronoss.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {WeatherTask.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract WeatherDao weatherDao();
}
