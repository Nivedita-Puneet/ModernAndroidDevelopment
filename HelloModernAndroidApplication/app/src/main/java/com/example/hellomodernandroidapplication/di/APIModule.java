package com.example.hellomodernandroidapplication.di;

import com.example.hellomodernandroidapplication.model.CountriesAPI;
import com.example.hellomodernandroidapplication.model.CountriesService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class APIModule {

    private static final String BASE_URL = "https://raw.githubusercontent.com/";

    @Provides
    public CountriesAPI provideCountriesAPI(){

        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(CountriesAPI.class);
    }
}
