package com.mcarr.optustechnicalevaluation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mcarr.optustechnicalevaluation.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**********************************
 * Created by Mikel on 22-Jun-16.
 *********************************/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick (R.id.activity_main_scenario_one_button) void onScenarioOneClicked() {
        Intent intent = new Intent(this, ScenarioOneActivity.class);
        startActivity(intent);
    }

    @OnClick (R.id.activity_main_scenario_two_button) void onScenarioTwoClicked() {
        Intent intent = new Intent(this, ScenarioTwoActivity.class);
        startActivity(intent);
    }
}
