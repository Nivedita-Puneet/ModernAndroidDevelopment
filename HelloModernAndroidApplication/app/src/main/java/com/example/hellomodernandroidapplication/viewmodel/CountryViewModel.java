package com.example.hellomodernandroidapplication.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hellomodernandroidapplication.di.DaggerAPIComponent;
import com.example.hellomodernandroidapplication.model.CountriesService;
import com.example.hellomodernandroidapplication.model.Country;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CountryViewModel extends ViewModel {

    public MutableLiveData<List<Country>> countriesLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> countryLoadError = new MutableLiveData<>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();

    @Inject
    public CountriesService countriesService;
    private CompositeDisposable disposable = new CompositeDisposable();

    public CountryViewModel() {

        DaggerAPIComponent.create().inject(this);
    }

    public void refresh(){
        fetchCountries();
    }
    private void fetchCountries() {

        /***
         * Moving from static to Dynamic data from REST API
         *
         * Country country = new Country("Albania", "Tirana", "");
        Country country1 = new Country("India", "Delhi", "");
        Country country2 = new Country("Brazil", "Brasilia", "");
        Country country3 = new Country("Czehia", "Prague", "");
        Country country4 = new Country("Australia", "Canberra", "");
        Country country5 = new Country("Newzealand", "Wellington", "");

        ArrayList<Country> countries = new ArrayList<>();
        countries.add(country);
        countries.add(country1);
        countries.add(country2);
        countries.add(country3);
        countries.add(country4);
        countries.add(country5);

        countriesLiveData.setValue(countries);
        countryLoadError.setValue(false);
        loading.setValue(false);*/

        loading.setValue(true);
        disposable.add(countriesService.getCountries()
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Country>>(){

                    @Override
                    public void onSuccess(@NonNull List<Country> countryList) {
                        countriesLiveData.setValue(countryList);
                        countryLoadError.setValue(false);
                        loading.setValue(false);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        countryLoadError.setValue(true);
                        loading.setValue(false);
                        e.printStackTrace();
                    }
                }));


    }
}
