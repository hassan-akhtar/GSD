package com.uaeemployee.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uaeemployee.Activites.BaseActivity;
import com.uaeemployee.Adapters.ListAdapter;
import com.uaeemployee.Models.Employee;
import com.uaeemployee.R;
import com.uaeemployee.Utils.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.List;

public class EmployeeSearchFragment extends Fragment {

    View mView;
    List<Employee> lEmployees = new ArrayList<Employee>();
    List<Employee> filteredList = new ArrayList<Employee>();
    EditText etSearch;
    ListView lvList;
    ListAdapter mAdapter;
    TextView tvNoTextFound;
    Toolbar toolbar;
    SharedPreferencesManager sharedPreferencesManager;

    public EmployeeSearchFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_employee_search, container, false);
        initViews();
        initObj();
        initListeners();
        populateListEmployee();

        mAdapter = new ListAdapter(lEmployees, getActivity());
        lvList.setAdapter(mAdapter);

        if (lEmployees.size() < 0)
            tvNoTextFound.setVisibility(View.VISIBLE);
        else
            tvNoTextFound.setVisibility(View.GONE);

        return mView;
    }

    private void initViews() {
        etSearch = (EditText) mView.findViewById(R.id.etSearch);
        lvList = (ListView) mView.findViewById(R.id.lvList);
        tvNoTextFound = (TextView) mView.findViewById(R.id.tvNoTextFound);
        toolbar = (Toolbar) mView.findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);
    }

    private void initObj() {
        BaseActivity.fragment = new VacanciesFragment();
        sharedPreferencesManager = new SharedPreferencesManager(getActivity());
        sharedPreferencesManager.setBoolean(SharedPreferencesManager.IS_VACANCY,false,getActivity());
    }

    private void initListeners() {

        etSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                cs = cs.toString().toLowerCase();

                filteredList.clear();

                for (int i = 0; i < lEmployees.size(); i++) {

                    final String name = lEmployees.get(i).getName().toLowerCase();
                    if (name.startsWith((String) cs)) {

                        filteredList.add(lEmployees.get(i));
                    }
                }

                if (0 == filteredList.size()) {
                    tvNoTextFound.setVisibility(View.VISIBLE);
                    mAdapter = new ListAdapter(filteredList, getActivity());
                    lvList.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                } else {
                    tvNoTextFound.setVisibility(View.GONE);
                    mAdapter = new ListAdapter(filteredList, getActivity());
                    lvList.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });

    }


    private void populateListEmployee() {
        Employee employee = new Employee("Hassan Akhtar", "Male", "Pakistan");
        lEmployees.add(employee);
        employee = new Employee("Neil Patel", "Male", "India");
        lEmployees.add(employee);
        employee = new Employee("Siri Devi", "Female", "Malaysia");
        lEmployees.add(employee);
        employee = new Employee("Fahad Sheikh", "Male", "UAE");
        lEmployees.add(employee);
        employee = new Employee("Mohsin Qureshi", "Male", "Pakistan");
        lEmployees.add(employee);
        employee = new Employee("Kashan Ali", "Male", "India");
        lEmployees.add(employee);
        employee = new Employee("Sharjeel Khan", "Male", "UAE");
        lEmployees.add(employee);
        employee = new Employee("Html Css Developer", "Male", "Pakistan");
        lEmployees.add(employee);
    }
}
