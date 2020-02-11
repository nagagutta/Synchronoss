package com.praveen.synchronoss.repository;

import com.praveen.synchronoss.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FeedApi {

    @GET("weather?lat=12.8320341&lon=77.646856&appid=5ad7218f2e11df834b0eaf3a33a39d2a")
    Call<WeatherResponse> getFeedResponse();
}
