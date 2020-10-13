package com.example.userdetail.apicall;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClass {
    private String END_POINT = "https://api.github.com";

    public RetroClass() {

    }


    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(END_POINT)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private APIService apiService = retrofit.create(APIService.class);


    public APIService getApiService() {
        return apiService;
    }
}
