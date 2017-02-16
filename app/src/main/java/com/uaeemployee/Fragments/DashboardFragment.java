package com.uaeemployee.Fragments;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.uaeemployee.Activites.BaseActivity;
import com.uaeemployee.Interfaces.Communicator;
import com.uaeemployee.R;

public class DashboardFragment extends Fragment {

    LinearLayout llOrganization, llVacancies, llEmployeeSearch, llEmployeeDocuments, llIncidentManagement, llPlan;
    View mView;
    Communicator myCommunicator;

    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        initViews();
        initObj();
        initListeners();

        return mView;
    }

    private void initViews() {
        //lvOrgs = (ListView) mView.findViewById(R.id.lvOrgs);
        llOrganization = (LinearLayout) mView.findViewById(R.id.llOrganization);
        llVacancies = (LinearLayout) mView.findViewById(R.id.llVacancies);
        llEmployeeSearch = (LinearLayout) mView.findViewById(R.id.llEmployeeSearch);
        llEmployeeDocuments = (LinearLayout) mView.findViewById(R.id.llEmployeeDocuments);
        llIncidentManagement = (LinearLayout) mView.findViewById(R.id.llIncidentManagement);
        llPlan = (LinearLayout) mView.findViewById(R.id.llPlan);
    }


    private void initObj() {
        myCommunicator = (Communicator) getActivity();
        BaseActivity.fragment = new DashboardFragment();
    }

    private void initListeners() {

        llOrganization.setOnClickListener(mGlobal_OnClickListener);
        llVacancies.setOnClickListener(mGlobal_OnClickListener);
        llEmployeeSearch.setOnClickListener(mGlobal_OnClickListener);
        llEmployeeDocuments.setOnClickListener(mGlobal_OnClickListener);
        llIncidentManagement.setOnClickListener(mGlobal_OnClickListener);
        llPlan.setOnClickListener(mGlobal_OnClickListener);


    }

    final View.OnClickListener mGlobal_OnClickListener = new View.OnClickListener() {
        public void onClick(final View v) {
            switch (v.getId()) {
                case R.id.llOrganization: {
                    BaseActivity.refreshMainViewByNew(new OrganizationFragment());
                }
                break;


                case R.id.llVacancies: {
                    BaseActivity.refreshMainViewByNew(new VacanciesFragment());
                }
                break;


                case R.id.llEmployeeSearch: {
                    BaseActivity.refreshMainViewByNew(new EmployeeSearchFragment());
                }
                break;


                case R.id.llEmployeeDocuments: {
                    BaseActivity.refreshMainViewByNew(new EmployeeDocumentFragment());
                }
                break;


                case R.id.llIncidentManagement: {
                    BaseActivity.refreshMainViewByNew(new IncidentManagmentFragment());
                }
                break;

                case R.id.llPlan: {
                    BaseActivity.refreshMainViewByNew(new EventAndSecurityFragment());
                }
                break;

            }
        }

    };

}
