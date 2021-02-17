package com.example.hellomodernandroidapplication.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.hellomodernandroidapplication.R;
import com.example.hellomodernandroidapplication.model.Country;
import com.example.hellomodernandroidapplication.viewmodel.CountryViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.countriesList)
    RecyclerView countries;

    @BindView(R.id.list_error)
    TextView errorList;

    @BindView(R.id.loading_view)
    ProgressBar loadingView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout refreshLayout;

    private CountryViewModel viewModel;
    private CountryListAdapter adapter = new CountryListAdapter(new ArrayList<>());

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        viewModel = ViewModelProviders.of(this).get(CountryViewModel.class);
        viewModel.refresh();

        countries.setLayoutManager(new LinearLayoutManager(this));
        countries.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(() -> {

            viewModel.refresh();
            refreshLayout.setRefreshing(false);
        });
        observeViewModel();
    }

    private void observeViewModel(){
        viewModel.countriesLiveData.observe(this, countryList -> {

            if(countryList != null){
                countries.setVisibility(View.VISIBLE);
                adapter.updateCountries(countryList);
            }
        });

        viewModel.countryLoadError.observe(this, isError -> {

            if(isError != null){
                errorList.setVisibility(isError ? View.VISIBLE:View.GONE);
            }

        });

        viewModel.loading.observe(this, loading -> {

            if(loading != null){
                loadingView.setVisibility(loading ? View.VISIBLE:View.GONE);

                if(loading){
                    errorList.setVisibility(View.GONE);
                    countries.setVisibility(View.GONE);
                }
            }
        });
    }
}
