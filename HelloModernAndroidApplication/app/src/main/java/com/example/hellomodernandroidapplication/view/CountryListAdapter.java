package com.example.hellomodernandroidapplication.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hellomodernandroidapplication.R;
import com.example.hellomodernandroidapplication.model.Country;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryViewHolder> {

    private List<Country> countryList;

    public CountryListAdapter(List<Country> countryList){
        this.countryList = countryList;
    }

    public void updateCountries(List<Country> newCountries){

        countryList.clear();
        countryList.addAll(newCountries);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {

        holder.bind(countryList.get(position));
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView)
        ImageView countryImage;

        @BindView(R.id.name)
        TextView countryName;

        @BindView(R.id.capital)
        TextView countryCapital;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Country country){

            countryName.setText(country.getName());
            countryCapital.setText(country.getCapital());
            Util.loadImage(countryImage, country.getFlag(), Util.getProgressDrawable(countryImage.getContext()));
        }
    }


}
