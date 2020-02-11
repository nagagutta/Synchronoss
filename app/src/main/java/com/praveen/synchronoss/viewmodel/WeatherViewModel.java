package com.praveen.synchronoss.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.praveen.synchronoss.model.WeatherResponse;
import com.praveen.synchronoss.repository.FeedRepository;
import com.praveen.synchronoss.util.NetworkUtil;

public class WeatherViewModel extends AndroidViewModel {

    private MutableLiveData<WeatherResponse> mutableLiveData;
    private FeedRepository feedRepository;
    public WeatherViewModel(@NonNull Application application) {
        super(application);
    }

    private LiveData<WeatherResponse> init(){
        // check for network connectivity
        if(NetworkUtil.isInternetAvailable(getApplication())) {
            feedRepository = FeedRepository.getInstance(getApplication());
            mutableLiveData = feedRepository.getWeatherData();
        }
        return mutableLiveData;
    }

    public LiveData<WeatherResponse> getWeatherRepository() {
        if (mutableLiveData != null){
            return mutableLiveData;
        } else {
            return init();
        }
    }
}
