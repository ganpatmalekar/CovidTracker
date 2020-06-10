package com.swap.covid_19tracker.fragments;

import android.app.SearchManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.swap.covid_19tracker.R;
import com.swap.covid_19tracker.adapters.CountryAdapter;
import com.swap.covid_19tracker.models.CountryModel;
import com.swap.covid_19tracker.ui.CovidViewModel;

import java.util.ArrayList;
import java.util.List;

public class CountryData extends Fragment {

    private List<CountryModel> countryModelList;
    private RecyclerView recyclerView;
    private CountryAdapter countryAdapter;
    private Toolbar toolbar;
    private CovidViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_country_data, container, false);

        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle("Country");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        setHasOptionsMenu(true);

        recyclerView = view.findViewById(R.id.recyclerView);
        countryModelList = new ArrayList<>();

        viewModel = new ViewModelProvider(this).get(CovidViewModel.class);
        viewModel.init();
        viewModel.getCountryRepository().observe(getViewLifecycleOwner(), countryModel -> {
            List<CountryModel> modelList = countryModel;
            if (countryModelList != null) {
                countryModelList.addAll(modelList);
                countryAdapter.notifyDataSetChanged();
            }
        });

        setUpRecyclerView();

        return view;
    }

    private void setUpRecyclerView() {
        if (countryAdapter == null) {
            countryAdapter = new CountryAdapter(getContext(), countryModelList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(countryAdapter);
            recyclerView.setNestedScrollingEnabled(true);
        } else {
            countryAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        ((AppCompatActivity) getActivity()).getMenuInflater().inflate(R.menu.menu, menu);

        // Initialize menu item
        MenuItem menuItem = menu.findItem(R.id.nav_search);

        // Initialize SearchView
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search country...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                List<CountryModel> modelList = new ArrayList<>();
                for (CountryModel model : countryModelList){
                    String country = model.getCountry().toLowerCase();
                    if (country.contains(newText)) {
                        modelList.add(model);
                    }
                }
                countryAdapter.setFilter(modelList);
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_search:
                return true;
        }
        return false;
    }
}
