package com.praveen.synchronoss;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.praveen.synchronoss.database.DatabaseClient;
import com.praveen.synchronoss.database.WeatherTask;
import com.praveen.synchronoss.databinding.ActivityMainBinding;
import com.praveen.synchronoss.model.WeatherResponse;
import com.praveen.synchronoss.util.SharedPreferencesUtil;
import com.praveen.synchronoss.viewmodel.WeatherViewModel;

import java.util.List;


public class MainActivity extends AppCompatActivity{
    TextView place_name_ed;
    TextView place_id_ed;
    TextView time_zone_ed;
    TextView sunraise_ed;
    TextView sunset_ed;
    TextView temp_ed;
    private WeatherViewModel mWeatherViewModel;
    WeatherTask weatherTask;
    private boolean isSaved = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weatherTask = new WeatherTask();
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        place_name_ed = activityMainBinding.placeNameEd;
        place_id_ed = activityMainBinding.placeIdEd;
        time_zone_ed = activityMainBinding.timeZoneEd;
        sunraise_ed = activityMainBinding.sunraiseEd;
        sunset_ed = activityMainBinding.sunsetEd;
        temp_ed = activityMainBinding.tempEd;

        if(SharedPreferencesUtil.readPreferences
                (MainActivity.this, "SYNCHSAVED", "").
                equalsIgnoreCase("SAVED")){
            getWeatherData();
        }else {
            mWeatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
            if (mWeatherViewModel.getWeatherRepository() != null) {
                mWeatherViewModel.getWeatherRepository().observe(this, this::updateWeatherData);
            } else {
                Toast.makeText(this, getString(R.string.network_error_msg), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void weatherDetails(List<WeatherTask> weatherTasks){
        place_name_ed.setText(weatherTasks.get(0).getCurrentLocation());
        place_id_ed.setText(weatherTasks.get(0).getPlaceId());
        time_zone_ed.setText((weatherTasks.get(0).getTimeZone()));
        sunraise_ed.setText(weatherTasks.get(0).getSunraiseTime());
        sunset_ed.setText(weatherTasks.get(0).getSunsetTime());
        temp_ed.setText(weatherTasks.get(0).getTemperature());
    }

    private void updateWeatherData(WeatherResponse weatherResponse){
        if(weatherResponse.getErrorMsg() != null){
            Toast.makeText(this, weatherResponse.getErrorMsg(), Toast.LENGTH_SHORT).show();
        } else {
            class SaveWeatherTask extends AsyncTask<Void, Void, Void> {

                @Override
                protected Void doInBackground(Void... voids) {
                    weatherTask = new WeatherTask();

                    weatherTask.setCurrentLocation(weatherResponse.getName());
                    weatherTask.setPlaceId(weatherResponse.getId());
                    weatherTask.setTimeZone(weatherResponse.getTimezone());
                    weatherTask.setSunraiseTime(weatherResponse.getSys().getSunrise());
                    weatherTask.setSunsetTime(weatherResponse.getSys().getSunset());
                    weatherTask.setTemperature(weatherResponse.getMain().getTemp());
                    //adding to database
                    try{
                        DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                                .weatherDao()
                                .insert(weatherTask);
                        SharedPreferencesUtil.savePreferences(MainActivity.this, "SYNCHSAVED",
                                "SAVED");
                        getWeatherData();
                    }catch (Exception ex){
                        Log.e("Main", "Exception :: "+ex);
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    Log.e("Main", "Saved success");
                }
            }
            SaveWeatherTask saveWeatherTask = new SaveWeatherTask();
            saveWeatherTask.execute();

        }
    }



    private List<WeatherTask> getWeatherData(){
        final List<WeatherTask>[] weatherTasks = new List[]{null};
        class GetWeatherTask extends AsyncTask<Void, Void, List<WeatherTask>>{

            @Override
            protected List<WeatherTask> doInBackground(Void... voids) {
                try{
                    weatherTasks[0] = DatabaseClient.getInstance(getApplicationContext())
                            .getAppDatabase()
                            .weatherDao()
                            .getAll();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            weatherDetails(weatherTasks[0]);
                        }
                    });

                }catch (Exception ex){
                    Log.e("Main", "Exception getWeatherData :: "+ex);
                }
                return weatherTasks[0];
            }

            @Override
            protected void onPostExecute(List<WeatherTask> weatherTasks) {
                super.onPostExecute(weatherTasks);
                //Log.e("Main", "retrieved success"+weatherTasks.get(0).getSunraiseTime());
            }
        }
        GetWeatherTask getWeatherTask = new GetWeatherTask();
        getWeatherTask.execute();

        return weatherTasks[0];
    }

}
