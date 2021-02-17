package com.example.hellomodernandroidapplication.model;

import com.example.hellomodernandroidapplication.di.DaggerAPIComponent;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
public class CountriesService {

    private static CountriesService instance;

    @Inject
    public CountriesAPI api;
    public CountriesService() {

        DaggerAPIComponent.create().inject(this);

    }

    public static CountriesService getInstance() {

        if (instance == null) {

            instance = new CountriesService();
        }

        return instance;
    }

    public Single<List<Country>> getCountries(){
        return api.getCountries();
    }

}
