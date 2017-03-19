package com.uaeemployee.Activites;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.uaeemployee.Adapters.ListAdapter;
import com.uaeemployee.Application.MyApplication;
import com.uaeemployee.Fragments.SubSubOrganizationFragment;
import com.uaeemployee.Fragments.VacanciesFragment;
import com.uaeemployee.Network.ResponseDTOs.EmployeeDTO;
import com.uaeemployee.Network.ResponseDTOs.EmployeeResponseDTO;
import com.uaeemployee.Network.ResponseDTOs.ResponseDTO;
import com.uaeemployee.Network.ResponseDTOs.VacanciesDTO;
import com.uaeemployee.Network.Service.GSDServiceFactory;
import com.uaeemployee.Network.Service.MyCallBack;
import com.uaeemployee.R;
import com.uaeemployee.Utils.CommonActions;
import com.uaeemployee.Utils.SharedPreferencesManager;
import com.uaeemployee.Utils.SystemConstants;

import java.util.ArrayList;
import java.util.List;

public class EmployeeSearchActivity extends AppCompatActivity  implements MyCallBack{


    List<EmployeeDTO> lEmployees = new ArrayList<EmployeeDTO>();
    List<EmployeeDTO> filteredList = new ArrayList<EmployeeDTO>();
    EditText etSearch;
    ListView lvList;
    ListAdapter mAdapter;
    TextView tvNoTextFound;
    private Toolbar mToolbar;
    String genderKey = "", countryKey = "";
    SharedPreferencesManager sharedpreferences;
    int organizationID, orgType, genderType;
    String nationality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_employee_search);

        initViews();
        initObj();
        initListeners();
        getAllEmployees();
        genderKey = sharedpreferences.getString(SharedPreferencesManager.CURRENT_GENDER, EmployeeSearchActivity.this);
        countryKey = sharedpreferences.getString(SharedPreferencesManager.CURRENT_NATIONALITY, EmployeeSearchActivity.this);


        mAdapter = new ListAdapter(lEmployees, EmployeeSearchActivity.this,"");
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
        BaseActivity.fragment = new SubSubOrganizationFragment();
        sharedpreferences = new SharedPreferencesManager(EmployeeSearchActivity.this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.employee_search));
        mToolbar.setNavigationIcon(R.drawable.back_icon);

        organizationID = getIntent().getIntExtra("org_Id", 0);
        orgType = getIntent().getIntExtra("org_type", 0);
        genderType = getIntent().getIntExtra("gender_type", 0);
        nationality = getIntent().getStringExtra("nationality");

        sharedpreferences.setBoolean(SharedPreferencesManager.IS_VACANCY, false, EmployeeSearchActivity.this);
    }


    private void getAllEmployees() {
        CommonActions.showProgressDialog(EmployeeSearchActivity.this);
        GSDServiceFactory.getService(getApplicationContext()).getEmployeesByGender(new com.uaeemployee.Network.RequestDTOs.VacanciesDTO(SystemConstants.RESPONSE_EMPLOYEES,organizationID,orgType,MyApplication.getInstance().getUserID(),genderType,nationality),this);
    }
    private void initListeners() {

        etSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                cs = cs.toString().toLowerCase();

                filteredList.clear();

                for (int i = 0; i < lEmployees.size(); i++) {

                    final String name = lEmployees.get(i).getFirstName().toLowerCase();
                    if (name.startsWith((String) cs)) {

                        filteredList.add(lEmployees.get(i));
                    }
                }

                if (0 == filteredList.size()) {
                    tvNoTextFound.setVisibility(View.VISIBLE);
                    mAdapter = new ListAdapter(filteredList, EmployeeSearchActivity.this,"");
                    lvList.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                } else {
                    tvNoTextFound.setVisibility(View.GONE);
                    mAdapter = new ListAdapter(filteredList, EmployeeSearchActivity.this,"");
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

        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(getApplicationContext(), EmployeeProfileActivity.class);
                if("".equals(etSearch.getText().toString().trim())){
                    EmployeeDTO employeeDTO = new EmployeeDTO(lEmployees.get(position).getOrganization(),lEmployees.get(position).getEmployeeID(), lEmployees.get(position).getSubSubOrganizationID(), lEmployees.get(position).getFirstName()
                            , lEmployees.get(position).getLastName(), lEmployees.get(position).getGender(), lEmployees.get(position).getEmail(), lEmployees.get(position).getAddress(), lEmployees.get(position).getContactNo(), lEmployees.get(position).getSalary(), lEmployees.get(position).getCountryName());
                    in.putExtra(getString(R.string.bundle_emp_dto), employeeDTO);
                }else{
                    EmployeeDTO employeeDTO = new EmployeeDTO(filteredList.get(position).getOrganization(),filteredList.get(position).getEmployeeID(), filteredList.get(position).getSubSubOrganizationID(), filteredList.get(position).getFirstName()
                            , filteredList.get(position).getLastName(), filteredList.get(position).getGender(), filteredList.get(position).getEmail(), filteredList.get(position).getAddress(), filteredList.get(position).getContactNo(), filteredList.get(position).getSalary(), filteredList.get(position).getCountryName());
                    in.putExtra(getString(R.string.bundle_emp_dto), employeeDTO);
                }

                startActivity(in);
            }
        });
    }
    @Override
    public void onSuccess(ResponseDTO responseDTO) {
        switch (responseDTO.getCallBackId()) {

            case SystemConstants.RESPONSE_EMPLOYEES:
                EmployeeResponseDTO employeeResponseDTO = (EmployeeResponseDTO) responseDTO;
                if (responseDTO != null) {
                    if (null==responseDTO.getMessage()) {
                        CommonActions.DismissesDialog();
                        lEmployees = employeeResponseDTO.getEmployeeDTO();
                        mAdapter = new ListAdapter(lEmployees, getApplicationContext(),"");
                        lvList.setAdapter(mAdapter);
                    } else {
                        CommonActions.DismissesDialog();
                        new AlertDialog.Builder(getApplicationContext())
                                .setTitle(getString(R.string.txt_Org))
                                .setMessage(responseDTO.getMessage())
                                .setNegativeButton(getString(R.string.txt_close), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })
                                .show();
                    }
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void onFailure(ResponseDTO errorDTO) {
        CommonActions.DismissesDialog();
        if (404 == MyApplication.getInstance().getStatusCode())
            Toast.makeText(getApplicationContext(), R.string.error_404_msg, Toast.LENGTH_LONG).show();
        else if (1 == MyApplication.getInstance().getStatusCode())
            Toast.makeText(getApplicationContext(), R.string.error_poor_con, Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(), R.string.error_con_timeout, Toast.LENGTH_LONG).show();
    }
}


