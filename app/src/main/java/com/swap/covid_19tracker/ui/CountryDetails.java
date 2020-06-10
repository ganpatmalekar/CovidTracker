package com.swap.covid_19tracker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.swap.covid_19tracker.R;

import java.util.ArrayList;

public class CountryDetails extends AppCompatActivity {

    private BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);

        barChart = findViewById(R.id.barChart);

        Bundle bundle = getIntent().getExtras();

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        assert bundle != null;
        barEntries.add(new BarEntry(0, bundle.getLong("TODAY_CASES")));
        barEntries.add(new BarEntry(1, bundle.getLong("DEATHS")));
        barEntries.add(new BarEntry(2, bundle.getLong("TODAY_DEATHS")));
        barEntries.add(new BarEntry(3, bundle.getLong("RECOVER")));
        barEntries.add(new BarEntry(4, bundle.getLong("ACTIVE")));
        barEntries.add(new BarEntry(5, bundle.getLong("CRITICAL")));

        BarDataSet dataSet = new BarDataSet(barEntries, bundle.getString("COUNTRY_NAME"));
        dataSet.setValueTextSize(10);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        ArrayList<String> xAxisLabel = new ArrayList();

        xAxisLabel.add("Today Cases");
        xAxisLabel.add("Deaths");
        xAxisLabel.add("Today Death");
        xAxisLabel.add("Recover");
        xAxisLabel.add("Active");
        xAxisLabel.add("Critical");

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
//                return super.getFormattedValue(value);
                return xAxisLabel.get((int) value);
            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);


        BarData barData = new BarData(dataSet);
        barChart.setData(barData);
        barChart.getDescription().setEnabled(false);
        barChart.animateY(3000);
    }
}