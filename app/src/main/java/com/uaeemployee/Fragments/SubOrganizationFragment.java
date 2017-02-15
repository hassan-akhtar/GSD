package com.uaeemployee.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.uaeemployee.Activites.BaseActivity;
import com.uaeemployee.Activites.GenderActivity;
import com.uaeemployee.Adapters.CustomExpandableListAdapter;
import com.uaeemployee.Models.SubCompaniesDummyData;
import com.uaeemployee.Models.SubCompanyData;
import com.uaeemployee.Models.SubSubCompanyData;
import com.uaeemployee.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubOrganizationFragment extends Fragment {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<SubCompanyData> expandableListTitle;
    SubCompaniesDummyData subCompaniesDummyData;

    HashMap<SubCompanyData, List<SubSubCompanyData>> expandableListDetail;
    View rootView;


    public SubOrganizationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_sub_organization, container, false);
        initViews();
        initObj();
        initListeners();


        // Inflate the layout for this fragment
        return rootView;
    }

    private void initViews() {
        subCompaniesDummyData = new SubCompaniesDummyData();
        expandableListView = (ExpandableListView) rootView.findViewById(R.id.expandableListView);
        expandableListDetail = subCompaniesDummyData.getData();

    }

    private void initObj() {
        BaseActivity.fragment = new SubOrganizationFragment();
        expandableListTitle = new ArrayList<SubCompanyData>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
    }

    private void initListeners() {

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity(), " Position:"+groupPosition+" Clicked",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getActivity(),
                        "Position: "+childPosition+" Clicked", Toast.LENGTH_SHORT
                ).show();
                startActivity(new Intent(getActivity(), GenderActivity.class));
                return false;
            }
        });

    }

}
