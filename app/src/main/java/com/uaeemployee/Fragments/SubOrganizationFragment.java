package com.uaeemployee.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uaeemployee.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubOrganizationFragment extends Fragment {


    public SubOrganizationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sub_organization, container, false);
    }

}
