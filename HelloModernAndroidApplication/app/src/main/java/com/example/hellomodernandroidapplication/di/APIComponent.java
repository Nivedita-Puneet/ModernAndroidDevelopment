package com.example.hellomodernandroidapplication.di;

import com.example.hellomodernandroidapplication.model.CountriesService;
import com.example.hellomodernandroidapplication.viewmodel.CountryViewModel;

import dagger.Component;

@Component(modules = {APIModule.class, NetworkServiceModule.class})
public interface APIComponent {

    void inject(CountriesService service);

    void inject(CountryViewModel countryViewModel);
}
