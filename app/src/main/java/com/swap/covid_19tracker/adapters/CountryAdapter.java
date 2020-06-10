package com.swap.covid_19tracker.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.swap.covid_19tracker.R;
import com.swap.covid_19tracker.models.CountryModel;
import com.swap.covid_19tracker.ui.CountryDetails;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private Context context;
    private List<CountryModel> countryModelList;

    public CountryAdapter(Context context, List<CountryModel> countryModelList) {
        this.context = context;
        this.countryModelList = countryModelList;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.country_row_layout, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {

        holder.textViewName.setText(countryModelList.get(position).getCountry());
        holder.textViewCases.setText(String.valueOf(countryModelList.get(position).getCases()));
        Glide.with(context).load(countryModelList.get(position).getCountryInfo().getFlag()).into(holder.imageView);

        itemClick(holder, position);
    }

    // RecyclerView item click
    public void itemClick(CountryViewHolder holder, int position) {
        CountryModel model = countryModelList.get(position);
        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CountryDetails.class);

            Bundle bundle = new Bundle();
            bundle.putString("COUNTRY_NAME", model.getCountry());
            bundle.putLong("TODAY_CASES", model.getTodayCases());
            bundle.putLong("DEATHS", model.getDeaths());
            bundle.putLong("TODAY_DEATHS", model.getTodayDeaths());
            bundle.putLong("RECOVER", model.getRecovered());
            bundle.putLong("ACTIVE", model.getActive());
            bundle.putLong("CRITICAL", model.getCritical());

            intent.putExtras(bundle);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return countryModelList.size();
    }

    // Filter adapter
    public void setFilter(List<CountryModel> modelList) {
        countryModelList = new ArrayList<>();
        countryModelList.addAll(modelList);
        notifyDataSetChanged();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewName, textViewCases;
        CardView cardView;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.country_flag);
            textViewName = itemView.findViewById(R.id.country_name);
            textViewCases = itemView.findViewById(R.id.country_cases);

            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
