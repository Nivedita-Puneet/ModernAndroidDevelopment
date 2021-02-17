package com.example.hellomodernandroidapplication.di;

import com.example.hellomodernandroidapplication.model.CountriesService;
import com.example.hellomodernandroidapplication.model.Country;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkServiceModule {


    @Provides
    public  CountriesService provideCountriesService() {

        return CountriesService.getInstance();

    }

}
