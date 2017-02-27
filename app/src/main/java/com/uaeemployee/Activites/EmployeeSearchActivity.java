package com.uaeemployee.Activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.uaeemployee.Adapters.ListAdapter;
import com.uaeemployee.Fragments.VacanciesFragment;
import com.uaeemployee.Network.ResponseDTOs.VacanciesDTO;
import com.uaeemployee.R;
import com.uaeemployee.Utils.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.List;

public class EmployeeSearchActivity extends AppCompatActivity {


    List<VacanciesDTO> lEmployees = new ArrayList<VacanciesDTO>();
    List<VacanciesDTO> filteredList = new ArrayList<VacanciesDTO>();
    EditText etSearch;
    ListView lvList;
    ListAdapter mAdapter;
    TextView tvNoTextFound;
    private Toolbar mToolbar;
    String genderKey = "", countryKey = "";
    SharedPreferencesManager sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_employee_search);

        initViews();
        initObj();
        initListeners();
        genderKey = sharedpreferences.getString(SharedPreferencesManager.CURRENT_GENDER, EmployeeSearchActivity.this);
        countryKey = sharedpreferences.getString(SharedPreferencesManager.CURRENT_NATIONALITY, EmployeeSearchActivity.this);

//        if ("pakistan".equals(countryKey)) {
//            if ("male".equals(genderKey)) {
//                populateListMalePakistan();
//            } else if ("female".equals(genderKey)) {
//                populateListFemalePakistan();
//            }
//        } else if ("india".equals(countryKey)) {
//
//            if ("male".equals(genderKey)) {
//                populateListMaleIndia();
//            } else if ("female".equals(genderKey)) {
//                populateListFemaleIndia();
//            }
//
//        } else if ("localonly".equals(countryKey)) {
//            populateListLocalEmployees();
//        } else if ("local".equals(countryKey)) {
//
//            if ("male".equals(genderKey)) {
//                populateListLocalMale();
//            } else if ("female".equals(genderKey)) {
//                populateListLocalFemale();
//            }
//        }


        mAdapter = new ListAdapter(lEmployees, EmployeeSearchActivity.this);
        lvList.setAdapter(mAdapter);

        if (lEmployees.size() < 0)
            tvNoTextFound.setVisibility(View.VISIBLE);
        else
            tvNoTextFound.setVisibility(View.GONE);
    }

    private void initViews() {
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        etSearch = (EditText) findViewById(R.id.etSearch);
        lvList = (ListView) findViewById(R.id.lvList);
        tvNoTextFound = (TextView) findViewById(R.id.tvNoTextFound);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void initObj() {
        BaseActivity.fragment = new VacanciesFragment();
        sharedpreferences = new SharedPreferencesManager(EmployeeSearchActivity.this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.employee_search));
        mToolbar.setNavigationIcon(R.drawable.back_icon);
        sharedpreferences.setBoolean(SharedPreferencesManager.IS_VACANCY,false,EmployeeSearchActivity.this);
    }

    private void initListeners() {

        etSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                cs = cs.toString().toLowerCase();

                filteredList.clear();

                for (int i = 0; i < lEmployees.size(); i++) {

                    final String name = lEmployees.get(i).getTitle().toLowerCase();
                    if (name.startsWith((String) cs)) {

                        filteredList.add(lEmployees.get(i));
                    }
                }

                if (0 == filteredList.size()) {
                    tvNoTextFound.setVisibility(View.VISIBLE);
                    mAdapter = new ListAdapter(filteredList, EmployeeSearchActivity.this);
                    lvList.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                } else {
                    tvNoTextFound.setVisibility(View.GONE);
                    mAdapter = new ListAdapter(filteredList, EmployeeSearchActivity.this);
                    lvList.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });


        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


//    private void populateListMalePakistan() {
//        Employee employee = new Employee("Hassan Akhtar", "Male", "Pakistan");
//        lEmployees.add(employee);
//        employee = new Employee("Qasim Bajwa", "Male", "Pakistan");
//        lEmployees.add(employee);
//        employee = new Employee("Fahad Sheikh", "Male", "Pakistan");
//        lEmployees.add(employee);
//        employee = new Employee("Mohsin Qureshi", "Male", "Pakistan");
//        lEmployees.add(employee);
//    }
//
//    private void populateListFemalePakistan() {
//        Employee employee = new Employee("Fatima Gull", "Female", "Pakistan");
//        lEmployees.add(employee);
//        employee = new Employee("Anum Bibi", "Female", "Pakistan");
//        lEmployees.add(employee);
//        employee = new Employee("Siri Devi", "Female", "Pakistan");
//        lEmployees.add(employee);
//        employee = new Employee("Ayesha Singh", "Female", "Pakistan");
//        lEmployees.add(employee);
//    }
//
//    private void populateListLocalMale() {
//        Employee employee = new Employee("Ali Husnain Sheikh", "Male", "UAE");
//        lEmployees.add(employee);
//        employee = new Employee("Sheikh Hassan", "Male", "UAE");
//        lEmployees.add(employee);
//        employee = new Employee("Naz Kumkum", "Male", "UAE");
//        lEmployees.add(employee);
//        employee = new Employee("Hussan Saeed", "Male", "UAE");
//        lEmployees.add(employee);
//    }
//
//    private void populateListLocalFemale() {
//        Employee employee = new Employee("Fatima Sheikh", "Female", "UAE");
//        lEmployees.add(employee);
//        employee = new Employee("Sheikh Sundus", "Female", "UAE");
//        lEmployees.add(employee);
//        employee = new Employee("Naz Kumkum", "Female", "UAE");
//        lEmployees.add(employee);
//        employee = new Employee("Sehar Nawaz", "Female", "UAE");
//        lEmployees.add(employee);
//    }
//
//    private void populateListMaleIndia() {
//        Employee employee = new Employee("Bahun Devan", "Male", "India");
//        lEmployees.add(employee);
//        employee = new Employee("Rahul Gill", "Male", "India");
//        lEmployees.add(employee);
//        employee = new Employee("Rohit Sheti", "Male", "India");
//        lEmployees.add(employee);
//        employee = new Employee("Arbab Kapoor", "Male", "India");
//        lEmployees.add(employee);
//    }
//
//    private void populateListLocalEmployees() {
//        Employee employee = new Employee("Ali Husnain Sheikh", "Male", "UAE");
//        lEmployees.add(employee);
//        employee = new Employee("Sheikh Hassan", "Male", "UAE");
//        lEmployees.add(employee);
//        employee = new Employee("Fatima Sheikh", "Female", "UAE");
//        lEmployees.add(employee);
//        employee = new Employee("Sheikh Sundus", "Female", "UAE");
//        lEmployees.add(employee);
//        employee = new Employee("Naz Kumkum", "Male", "UAE");
//        lEmployees.add(employee);
//        employee = new Employee("Hussan Saeed", "Male", "UAE");
//        lEmployees.add(employee);
//        employee = new Employee("Naz Kumkum", "Female", "UAE");
//        lEmployees.add(employee);
//        employee = new Employee("Sehar Nawaz", "Female", "UAE");
//        lEmployees.add(employee);
//    }
//
//    private void populateListFemaleIndia() {
//        Employee employee = new Employee("Deepika Padukon", "Female", "India");
//        lEmployees.add(employee);
//        employee = new Employee("Siri Devi", "Female", "India");
//        lEmployees.add(employee);
//        employee = new Employee("Farah Khan", "Female", "India");
//        lEmployees.add(employee);
//        employee = new Employee("Enkita Sharma", "Female", "India");
//        lEmployees.add(employee);
//    }
}

