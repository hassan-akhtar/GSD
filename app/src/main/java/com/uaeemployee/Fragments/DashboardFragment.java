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

    Button btnOrganization, btnVacancies, btnEmployeeSearch, btnEmployeeDocuments, btnIncidentManagement, btnPlan;
    View mView;
    Communicator myCommunicator;

    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_dashboard, container, false);
        initViews();
        initObj();
        initListeners();

        return mView;
    }

    private void initViews() {
        btnOrganization = (Button) mView.findViewById(R.id.btnOrg);
        btnVacancies = (Button) mView.findViewById(R.id.btnVacancies);
        btnEmployeeSearch = (Button) mView.findViewById(R.id.btnEmployeeSearch);
        btnEmployeeDocuments = (Button) mView.findViewById(R.id.btnEmployeeDocs);
        btnIncidentManagement = (Button) mView.findViewById(R.id.btnIncidentManagement);
        btnPlan = (Button) mView.findViewById(R.id.btnEventsPlans);
        
        Drawable dOrg = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(((BitmapDrawable) getResources().getDrawable(R.drawable.icon_orgs)).getBitmap(), 130, 130, true));
        Drawable dVacancy = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(((BitmapDrawable) getResources().getDrawable(R.drawable.vancancyy)).getBitmap(), 130, 130, true));
        Drawable dEmployeeSearch = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(((BitmapDrawable) getResources().getDrawable(R.drawable.icon_search)).getBitmap(), 130, 130, true));
        Drawable dEmployeeDoc = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(((BitmapDrawable) getResources().getDrawable(R.drawable.documents)).getBitmap(), 130, 130, true));
        Drawable dEventsAndPlans = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(((BitmapDrawable) getResources().getDrawable(R.drawable.eventplan)).getBitmap(), 130, 130, true));
        Drawable dIncidentManagement = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(((BitmapDrawable) getResources().getDrawable(R.drawable.vancancyy)).getBitmap(), 130, 130, true));
        //setCompoundDrawablesWithIntrinsicBounds (image to left, top, right, bottom)
        btnOrganization.setCompoundDrawablesWithIntrinsicBounds(null,dOrg,null,null);
        btnVacancies.setCompoundDrawablesWithIntrinsicBounds(null,dVacancy,null,null);
        btnEmployeeSearch.setCompoundDrawablesWithIntrinsicBounds(null,dEmployeeSearch,null,null);
        btnEmployeeDocuments.setCompoundDrawablesWithIntrinsicBounds(null,dEmployeeDoc,null,null);
        btnIncidentManagement.setCompoundDrawablesWithIntrinsicBounds(null,dEventsAndPlans,null,null);
        btnPlan.setCompoundDrawablesWithIntrinsicBounds(null,dIncidentManagement,null,null);

    }

    private void initObj() {
        myCommunicator = (Communicator) getActivity();
        BaseActivity.fragment = new DashboardFragment();
    }

    private void initListeners() {

        btnOrganization.setOnClickListener(mGlobal_OnClickListener);
        btnVacancies.setOnClickListener(mGlobal_OnClickListener);
        btnEmployeeSearch.setOnClickListener(mGlobal_OnClickListener);
        btnEmployeeDocuments.setOnClickListener(mGlobal_OnClickListener);
        btnIncidentManagement.setOnClickListener(mGlobal_OnClickListener);
        btnPlan.setOnClickListener(mGlobal_OnClickListener);
    }

    final View.OnClickListener mGlobal_OnClickListener = new View.OnClickListener() {
        public void onClick(final View v) {
            switch (v.getId()) {
                case R.id.btnOrg: {
                    myCommunicator.setTitle("Organizations");
                    BaseActivity.refreshMainViewByNew(new OrganizationFragment());
                    break;
                }

                case R.id.btnVacancies: {
                    myCommunicator.setTitle("Vacancies");
                    BaseActivity.refreshMainViewByNew(new VacanciesFragment());
                    break;
                }

                case R.id.btnEmployeeSearch: {
                    myCommunicator.setTitle("Employee Search");
                    BaseActivity.refreshMainViewByNew(new EmployeeSearchFragment());
                    break;
                }

                case R.id.btnEmployeeDocs: {
                    myCommunicator.setTitle("Employee Documents");
                    BaseActivity.refreshMainViewByNew(new EmployeeDocumentFragment());
                    break;
                }

                case R.id.btnIncidentManagement: {
                    myCommunicator.setTitle("Incident Management");
                    BaseActivity.refreshMainViewByNew(new IncidentManagmentFragment());
                    break;
                }

                case R.id.btnEventsPlans: {
                    myCommunicator.setTitle("Event And Security Plan");
                    BaseActivity.refreshMainViewByNew(new EventAndSecurityFragment());
                    break;
                }

            }
        }

    };

}
