package com.swap.covid_19tracker.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.swap.covid_19tracker.ui.CovidViewModel;
import com.swap.covid_19tracker.R;

import java.sql.Timestamp;
import java.util.Date;

public class Dashboard extends Fragment {

    private TextView textViewAffected, textViewDeath, textViewRecovered, textViewCritical, textViewLastUpdated;
    private CovidViewModel covidViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        textViewAffected = view.findViewById(R.id.textView_affected);
        textViewDeath = view.findViewById(R.id.textView_death);
        textViewRecovered = view.findViewById(R.id.textView_recovered);
        textViewCritical = view.findViewById(R.id.textView_critical);

        textViewLastUpdated = view.findViewById(R.id.last_updated);

        covidViewModel = new ViewModelProvider(this).get(CovidViewModel.class);
        covidViewModel.init();
        covidViewModel.getCovidRepository().observe(getViewLifecycleOwner(), globalModel -> {

            textViewAffected.setText(String.valueOf(globalModel.getCases()));
            textViewDeath.setText(String.valueOf(globalModel.getDeaths()));
            textViewRecovered.setText(String.valueOf(globalModel.getRecovered()));
            textViewCritical.setText(String.valueOf(globalModel.getCritical()));

            long updatedDate = globalModel.getUpdated();
            Timestamp timestamp = new Timestamp(updatedDate);
            Date date = new Date(timestamp.getTime());
            textViewLastUpdated.setText("Last Updated: " + date);
        });

        return view;
    }
}
