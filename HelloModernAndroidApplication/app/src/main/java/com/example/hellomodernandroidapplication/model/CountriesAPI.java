package com.example.hellomodernandroidapplication.model;

import java.util.List;
import retrofit2.http.GET;

import io.reactivex.Single;

public interface CountriesAPI {

    @GET("DevTides/countries/master/countriesV2.json")
    Single<List<Country>> getCountries();
}
