package com.uaeemployee.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.uaeemployee.Activites.BaseActivity;
import com.uaeemployee.Activites.IncidentDetailActivity;
import com.uaeemployee.R;


public class IncidentManagmentFragment extends Fragment {

    View mView;
    ListView lvOrganizations;
    String[] orgs;

    public IncidentManagmentFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.fragment_incident_managment, container, false);
        initViews();
        initObj();
        initListeners();


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, orgs);
        lvOrganizations.setAdapter(adapter);

        return mView;
    }

    private void initViews() {
        lvOrganizations = (ListView) mView.findViewById(R.id.lvOrganizations);
    }

    private void initObj() {
        BaseActivity.fragment = new IncidentManagmentFragment();

        orgs = new String[] { "Organization One",
                "Organization Two",
                "Organization Three",
                "Organization Four",
                "Organization Five",
                "Organization Six",
                "Organization Seven",
                "Organization Eight"
        };
    }

    private void initListeners() {
        lvOrganizations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(),IncidentDetailActivity.class));
            }
        });

    }
}
