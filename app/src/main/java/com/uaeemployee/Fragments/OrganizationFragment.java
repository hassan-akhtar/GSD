package com.uaeemployee.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.uaeemployee.Activites.BaseActivity;
import com.uaeemployee.Activites.GenderActivity;
import com.uaeemployee.Interfaces.Communicator;
import com.uaeemployee.R;


public class OrganizationFragment extends Fragment {

    View mView;
    LinearLayout llOrg1, llOrg2, llOrg3 , llOrg4;
    Communicator myCommunicator;
    //ListView lvOrganizations;

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
        // lvOrganizations = (ListView) mView.findViewById(R.id.lvOrganizations);
        llOrg1 = (LinearLayout) mView.findViewById(R.id.llOrg1);
        llOrg2 = (LinearLayout) mView.findViewById(R.id.llOrg2);
        llOrg3 = (LinearLayout) mView.findViewById(R.id.llOrg3);
        llOrg4 = (LinearLayout) mView.findViewById(R.id.llOrg4);
    }

    private void initObj() {
        myCommunicator = (Communicator) getActivity();
        BaseActivity.fragment = new OrganizationFragment();
    }

    private void initListeners() {
       /* lvOrganizations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });*/
        llOrg1.setOnClickListener(mGlobal_OnClickListener);
        llOrg2.setOnClickListener(mGlobal_OnClickListener);
        llOrg3.setOnClickListener(mGlobal_OnClickListener);
        llOrg4.setOnClickListener(mGlobal_OnClickListener);
    }

    final View.OnClickListener mGlobal_OnClickListener = new View.OnClickListener() {
        public void onClick(final View v) {
            switch (v.getId()) {
                case R.id.llOrg1: {
                    startActivity(new Intent(getActivity(), GenderActivity.class));
                    break;
                }

                case R.id.llOrg2: {
                    startActivity(new Intent(getActivity(), GenderActivity.class));
                    break;
                }

                case R.id.llOrg3: {
                    startActivity(new Intent(getActivity(), GenderActivity.class));
                    break;
                }

                case R.id.llOrg4: {
                    startActivity(new Intent(getActivity(), GenderActivity.class));
                    break;
                }

            }
        }

    };

}
