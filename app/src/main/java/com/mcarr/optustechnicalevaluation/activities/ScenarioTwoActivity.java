package com.mcarr.optustechnicalevaluation.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.mcarr.optustechnicalevaluation.R;
import com.mcarr.optustechnicalevaluation.adapters.NavigationRecyclerAdapter;
import com.mcarr.optustechnicalevaluation.event.NavigationItemsEvent;
import com.mcarr.optustechnicalevaluation.object.LocationItem;
import com.mcarr.optustechnicalevaluation.object.NavigationItem;
import com.mcarr.optustechnicalevaluation.otto.BusProvider;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import icepick.Icepick;
import icepick.State;

/**********************************
 * Created by Mikel on 22-Jun-16.
 *********************************/
public class ScenarioTwoActivity extends AppCompatActivity {

    private ArrayList<NavigationItem> mResults;
    private ArrayList<String> mResultNames = new ArrayList<>();
    private NavigationRecyclerAdapter mAdapter;
    private ArrayAdapter<String> mSpinnerArrayAdapter;

    @BindView(R.id.scenario_two_recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.scenario_two_spinner) Spinner mSpinner;

    @State protected int mSpinnerSelection = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenario_two);
        ButterKnife.bind(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
        setUpSpinner();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @OnClick(R.id.scenario_two_navigate) void onNavigate() {
        LocationItem locationItem = mResults.get(mSpinnerSelection).getLocation();
        Uri gmmIntentUri = Uri.parse(getString(R.string.geo_string, locationItem.getLatitude(), locationItem.getLongitude()));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }

    private void setUpSpinner() {
        mSpinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mResultNames);
        mSpinner.setAdapter(mSpinnerArrayAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSpinnerSelection = position;
                mAdapter = new NavigationRecyclerAdapter(mResults.get(position).getFromcentral());
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
        BusProvider.getInstance().post(new NavigationItemsEvent.OnLoadingStart());
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    @Subscribe
    public void onNavigationItemsLoaded(NavigationItemsEvent.OnLoaded onLoaded) {
        mResults = onLoaded.getResponse();
        mResultNames.clear();
        for (NavigationItem navigationItem : mResults) {
            mResultNames.add(navigationItem.getName());
        }
        mSpinnerArrayAdapter.notifyDataSetChanged();
        mSpinner.setSelection(mSpinnerSelection);
    }

    @Subscribe
    public void onNavigationItemsFailed(NavigationItemsEvent.OnLoadingError onLoadingFailed) {
        Toast.makeText(this, getString(R.string.navigation_call_failed), Toast.LENGTH_LONG).show();
    }
}