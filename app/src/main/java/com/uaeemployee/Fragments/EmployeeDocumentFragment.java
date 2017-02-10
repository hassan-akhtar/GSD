package com.uaeemployee.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.uaeemployee.Activites.BaseActivity;
import com.uaeemployee.R;

public class EmployeeDocumentFragment extends Fragment {

    View mView;

    public EmployeeDocumentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_employee_document, container, false);
        initViews();
        initObj();
        initListeners();

        return mView;
    }

    private void initViews() {
    }

    private void initObj() {  BaseActivity.fragment = new EmployeeDocumentFragment();
    }

    private void initListeners() {

    }
}
