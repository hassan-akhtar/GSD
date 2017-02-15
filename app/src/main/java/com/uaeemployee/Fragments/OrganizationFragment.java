package com.uaeemployee.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.uaeemployee.Activites.BaseActivity;
import com.uaeemployee.Adapters.OrganizationAdapter;
import com.uaeemployee.Interfaces.Communicator;
import com.uaeemployee.Models.Organization;
import com.uaeemployee.R;

import java.util.ArrayList;
import java.util.List;


public class OrganizationFragment extends Fragment {

    View mView;
    Communicator myCommunicator;
    ListView lvOrgs;
    OrganizationAdapter adapter;
    public List<Organization> organizationsList = new ArrayList<>();

    public OrganizationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_org, container, false);
        initViews();
        initObj();
        initListeners();
        return mView;
    }

    private void initViews() {
        lvOrgs = (ListView) mView.findViewById(R.id.lvOrgs);

    }

    private void initObj() {

        myCommunicator = (Communicator) getActivity();
        BaseActivity.fragment = new OrganizationFragment();

        Organization organization = new Organization("One Company ", "75");
        organizationsList.add(organization);
        organization = new Organization("Two Company ", "10");
        organizationsList.add(organization);
        organization = new Organization("Three Company ", "17");
        organizationsList.add(organization);
        organization = new Organization("Four Company ", "108");
        organizationsList.add(organization);
        organization = new Organization("Five Company ", "40");
        organizationsList.add(organization);
        organization = new Organization("Six Company ", "17");
        organizationsList.add(organization);
        organization = new Organization("Seven Company ", "108");
        organizationsList.add(organization);
        organization = new Organization("Eight Company ", "40");
        organizationsList.add(organization);


        adapter = new OrganizationAdapter(organizationsList, getActivity());
        lvOrgs.setAdapter(adapter);
    }

    private void initListeners() {
        lvOrgs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

              //  Toast.makeText(getActivity(),""+position,Toast.LENGTH_LONG).show();
                BaseActivity.refreshMainViewByNew(new SubOrganizationFragment());
            }
        });

    }

    final View.OnClickListener mGlobal_OnClickListener = new View.OnClickListener() {
        public void onClick(final View v) {
            switch (v.getId()) {

            }
        }

    };

}
