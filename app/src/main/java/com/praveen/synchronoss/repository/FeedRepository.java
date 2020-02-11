package com.praveen.synchronoss.repository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.praveen.synchronoss.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedRepository {

    private static FeedRepository feedRepository;
    private FeedApi feedApi;
    private MutableLiveData<WeatherResponse> feedData;

    private FeedRepository(Context context){
        feedApi = RetrofitService.createService(FeedApi.class, context);
    }

    public static FeedRepository getInstance(Context context){
        if (feedRepository == null){
            feedRepository = new FeedRepository(context);
        }
        return feedRepository;
    }

    public MutableLiveData<WeatherResponse> getWeatherData(){
        feedData = new MutableLiveData<>();
        feedApi.getFeedResponse().enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()){
                    feedData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                // set and send error message to ui
                WeatherResponse feedResponse = new WeatherResponse();
                feedResponse.setErrorMsg(t.getMessage());
                feedData.setValue(feedResponse);
            }
        });
        return feedData;
    }
}
