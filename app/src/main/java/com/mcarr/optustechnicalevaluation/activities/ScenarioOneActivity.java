package com.mcarr.optustechnicalevaluation.activities;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mcarr.optustechnicalevaluation.fragments.FragmentMain;
import com.mcarr.optustechnicalevaluation.R;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import icepick.Icepick;
import icepick.State;

public class ScenarioOneActivity extends AppCompatActivity {

    private static final int NUMBER_OF_TABS = 5;

    @BindView(R.id.scenario_one_tab_layout) TabLayout mTabLayout;
    @BindView(R.id.scenario_one_viewpager) ViewPager mViewPager;
    @BindView(R.id.scenario_one_textView) TextView mTabSelectedTextView;
    @BindViews({ R.id.scenario_one_button_1, R.id.scenario_one_button_2, R.id.scenario_one_button_3 })
    List<Button> mColourButtons;
    @BindView(R.id.scenario_one_button_layout) LinearLayout mButtonBackground;

    @State protected int mSelectedTab = 0;
    @State protected int mLayoutBackgroundColour = Color.TRANSPARENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenario_one);
        ButterKnife.bind(this);
        setUpTabs();
        setUpColourButtons();
        setUpViewPager();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        mButtonBackground.setBackgroundColor(mLayoutBackgroundColour);

        mTabLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mTabLayout.getTabAt(mSelectedTab).select();
                mTabLayout.setScrollPosition(mSelectedTab, 0f, true);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    private void setUpColourButtons() {
        mColourButtons.get(0).setTag(Color.RED);
        mColourButtons.get(1).setTag(Color.BLUE);
        mColourButtons.get(2).setTag(Color.GREEN);

        for (Button button : mColourButtons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mLayoutBackgroundColour = (int)v.getTag();
                    mButtonBackground.setBackgroundColor(mLayoutBackgroundColour);
                }
            });
        }
    }

    private void setUpTabs() {
        for (int i = 0; i < NUMBER_OF_TABS; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.tab_name, (i + 1))));
        }

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mSelectedTab = tab.getPosition();
                mTabSelectedTextView.setText(tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                mTabSelectedTextView.setText(tab.getText());
            }
        });
    }

    private void setUpViewPager() {
        PagerAdapter sectionsPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(sectionsPagerAdapter);
    }

    public class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentMain.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + (position + 1);
        }
    }
}
