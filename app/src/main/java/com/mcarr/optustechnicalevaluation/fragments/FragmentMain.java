package com.mcarr.optustechnicalevaluation.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mcarr.optustechnicalevaluation.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**********************************
 * Created by Mikel on 21-Jun-16.
 *********************************/
public class FragmentMain extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    @BindView (R.id.fragment_main_label) TextView mTextView;

    private int mSectionNumber;

    public FragmentMain() {}

    public static FragmentMain newInstance(int sectionNumber) {
        FragmentMain fragment = new FragmentMain();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        mSectionNumber = getArguments().getInt(ARG_SECTION_NUMBER, -1);
        mTextView.setText(getString(R.string.section_format, mSectionNumber));
        return rootView;
    }

    @OnClick(R.id.fragment_main_layout) void layoutClicked() {
        Toast.makeText(getActivity(), getString(R.string.fragment_click, mSectionNumber), Toast.LENGTH_SHORT).show();
    }


}