package com.uaeemployee.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.uaeemployee.Activites.BaseActivity;
import com.uaeemployee.Adapters.ListAdapter;
import com.uaeemployee.Models.Employee;
import com.uaeemployee.R;
import com.uaeemployee.Utils.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.List;

public class VacanciesFragment extends Fragment {

    View mView;
    List<Employee> lVacancies = new ArrayList<Employee>();
    List<Employee> filteredList = new ArrayList<Employee>();
    EditText etSearch;
    ListView lvList;
    ListAdapter mAdapter;
    TextView tvNoTextFound;
    SharedPreferencesManager sharedPreferencesManager;

    public VacanciesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_vacancies, container, false);
        initViews();
        initObj();
        initListeners();
        populateListVacancies();

        mAdapter = new ListAdapter(lVacancies, getActivity());
        lvList.setAdapter(mAdapter);

        if (lVacancies.size() < 0)
            tvNoTextFound.setVisibility(View.VISIBLE);
        else
            tvNoTextFound.setVisibility(View.GONE);

        return mView;
    }

    private void initViews() {
        etSearch = (EditText) mView.findViewById(R.id.etSearch);
        lvList = (ListView) mView.findViewById(R.id.lvList);
        tvNoTextFound = (TextView) mView.findViewById(R.id.tvNoTextFound);
    }

    private void initObj() {
        BaseActivity.fragment = new VacanciesFragment();
        sharedPreferencesManager = new SharedPreferencesManager(getActivity());
        sharedPreferencesManager.setBoolean(SharedPreferencesManager.IS_VACANCY,true,getActivity());
    }

    private void initListeners() {

        etSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                cs = cs.toString().toLowerCase();

                filteredList.clear();

                for (int i = 0; i < lVacancies.size(); i++) {

                    final String name = lVacancies.get(i).getName().toLowerCase();
                    if (name.startsWith((String) cs)) {

                        filteredList.add(lVacancies.get(i));
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


    private void populateListVacancies() {
        Employee employee = new Employee("Dot Net Developer", "Male", "Full time");
        lVacancies.add(employee);
        employee = new Employee("Marketing Analyst", "Male", "Full time");
        lVacancies.add(employee);
        employee = new Employee("Android Developer", "Male", "Full time");
        lVacancies.add(employee);
        employee = new Employee("iOS Developer", "Female", "Full time");
        lVacancies.add(employee);
        employee = new Employee("Php Developer", "Male", "Full time");
        lVacancies.add(employee);
        employee = new Employee("Vb Developer", "Female", "Full time");
        lVacancies.add(employee);
        employee = new Employee("Angular Js", "Female", "Full time");
        lVacancies.add(employee);
        employee = new Employee("Html Css Developer", "Male", "Full time");
        lVacancies.add(employee);
    }
}
