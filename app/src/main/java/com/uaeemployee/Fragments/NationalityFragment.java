package com.uaeemployee.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uaeemployee.R;


public class NationalityFragment extends Fragment {

    View mView;

    public NationalityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_nationality, container, false);
        initViews();
        initObj();
        initListeners();
        return mView;
    }

    private void initViews() {
    }

    private void initObj() {
    }

    private void initListeners() {

    }

}
