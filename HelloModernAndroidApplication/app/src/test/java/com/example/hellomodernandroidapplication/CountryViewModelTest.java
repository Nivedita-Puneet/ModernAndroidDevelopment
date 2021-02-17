package com.example.hellomodernandroidapplication;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.hellomodernandroidapplication.model.CountriesService;
import com.example.hellomodernandroidapplication.model.Country;
import com.example.hellomodernandroidapplication.viewmodel.CountryViewModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

public class CountryViewModelTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    CountriesService countriesService;

    @InjectMocks
    CountryViewModel countryViewModel = new CountryViewModel();

    private Single<List<Country>> testSingle;


    @Before
    public  void setUp(){

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCountriesSuccess(){

        Country country = new Country("name", "capital", "flag");
        ArrayList<Country>  countries = new ArrayList<>();
        countries.add(country);

        testSingle = Single.just(countries);

        Mockito.when(countriesService.getCountries()).thenReturn(testSingle);

        countryViewModel.refresh();

        Assert.assertEquals(1, countryViewModel.countriesLiveData.getValue().size());
        Assert.assertEquals(false, countryViewModel.countryLoadError.getValue());
        Assert.assertEquals(false, countryViewModel.loading.getValue());

    }

    @Test
    public void getCountriesFailure()
    {

        testSingle = Single.error(new Throwable());


        Mockito.when(countriesService.getCountries()).thenReturn(testSingle);

        countryViewModel.refresh();

        Assert.assertEquals(true, countryViewModel.countryLoadError.getValue());
        Assert.assertEquals(false, countryViewModel.loading.getValue());


    }
    @Before
    public void setUpRxSchedulers(){
        Scheduler immediate = new Scheduler() {
            @NonNull
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(runnable -> {

                    runnable.run();

                }, true);
            }
        };

        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }

}
