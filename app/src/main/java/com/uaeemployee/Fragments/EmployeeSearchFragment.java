package com.uaeemployee.Fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.uaeemployee.Activites.BaseActivity;
import com.uaeemployee.Activites.EmployeeDocumentActivity;
import com.uaeemployee.Activites.EmployeeProfileActivity;
import com.uaeemployee.Activites.VacancyDetailActivity;
import com.uaeemployee.Adapters.ListAdapter;
import com.uaeemployee.Application.MyApplication;
import com.uaeemployee.Models.Employee;
import com.uaeemployee.Network.ResponseDTOs.EmployeeDTO;
import com.uaeemployee.Network.ResponseDTOs.EmployeeResponseDTO;
import com.uaeemployee.Network.ResponseDTOs.ResponseDTO;
import com.uaeemployee.Network.ResponseDTOs.VacanciesDTO;
import com.uaeemployee.Network.ResponseDTOs.VacanciesResponseDTO;
import com.uaeemployee.Network.Service.GSDServiceFactory;
import com.uaeemployee.Network.Service.MyCallBack;
import com.uaeemployee.R;
import com.uaeemployee.Utils.CommonActions;
import com.uaeemployee.Utils.SharedPreferencesManager;
import com.uaeemployee.Utils.SystemConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class EmployeeSearchFragment extends Fragment implements MyCallBack {

    View mView;
    static List<EmployeeDTO> lEmployees = new ArrayList<EmployeeDTO>();
    static List<EmployeeDTO> lEmployeesFiltered = new ArrayList<EmployeeDTO>();
    List<EmployeeDTO> filteredList = new ArrayList<EmployeeDTO>();
    EditText etSearch;
    static ListView lvList;
    static ListAdapter mAdapter;
    TextView tvNoTextFound;
    Toolbar toolbar;
    boolean compFilterApplied = false;
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
        getAllEmployees();

        if (lEmployees.size() < 0)
            tvNoTextFound.setVisibility(View.VISIBLE);
        else
            tvNoTextFound.setVisibility(View.GONE);

        return mView;
    }

    private void getAllEmployees() {
        CommonActions.showProgressDialog(getActivity());
        GSDServiceFactory.getService(getActivity()).getEmployees(new com.uaeemployee.Network.RequestDTOs.VacanciesDTO(SystemConstants.RESPONSE_EMPLOYEES, MyApplication.getInstance().getUserID()), this);
    }

    private void initViews() {
        etSearch = (EditText) mView.findViewById(R.id.etSearch);
        lvList = (ListView) mView.findViewById(R.id.lvList);
        tvNoTextFound = (TextView) mView.findViewById(R.id.tvNoTextFound);
        toolbar = (Toolbar) mView.findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);
        if (!BaseActivity.isEmployeeDoc) {
            ((BaseActivity) getActivity()).mToolbar.setTitle(getString(R.string.employeeSearch));
        } else {
            ((BaseActivity) getActivity()).mToolbar.setTitle(getString(R.string.employee_document));
        }
    }

    private void initObj() {
        BaseActivity.fragment = new EmployeeSearchFragment();
        sharedPreferencesManager = new SharedPreferencesManager(getActivity());
        sharedPreferencesManager.setBoolean(SharedPreferencesManager.IS_VACANCY, false, getActivity());
    }

    private void initListeners() {

        etSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                cs = cs.toString().toLowerCase();

                filteredList.clear();

                if (!compFilterApplied) {
                    for (int i = 0; i < lEmployees.size(); i++) {

                        final String name = lEmployees.get(i).getFirstName().toLowerCase();
                        if (name.startsWith((String) cs)) {

                            filteredList.add(lEmployees.get(i));
                        }
                    }

                    if (0 == filteredList.size()) {
                        tvNoTextFound.setVisibility(View.VISIBLE);
                        mAdapter = new ListAdapter(filteredList, getActivity(), "");
                        lvList.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    } else {
                        tvNoTextFound.setVisibility(View.GONE);
                        mAdapter = new ListAdapter(filteredList, getActivity(), "");
                        lvList.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }
                } else {
                    for (int i = 0; i < lEmployeesFiltered.size(); i++) {

                        final String name = lEmployeesFiltered.get(i).getFirstName().toLowerCase();
                        if (name.startsWith((String) cs)) {

                            filteredList.add(lEmployeesFiltered.get(i));
                        }
                    }

                    if (0 == filteredList.size()) {
                        tvNoTextFound.setVisibility(View.VISIBLE);
                        mAdapter = new ListAdapter(filteredList, getActivity(), "");
                        lvList.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    } else {
                        tvNoTextFound.setVisibility(View.GONE);
                        mAdapter = new ListAdapter(filteredList, getActivity(), "");
                        lvList.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });


        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!BaseActivity.isEmployeeDoc) {
                    Intent in = new Intent(getActivity(), EmployeeProfileActivity.class);
                    if ("".equals(etSearch.getText().toString().trim())&& !compFilterApplied) {
                        EmployeeDTO employeeDTO = new EmployeeDTO(lEmployees.get(position).getOrganization(), lEmployees.get(position).getEmployeeID(), lEmployees.get(position).getSubSubOrganizationID(), lEmployees.get(position).getFirstName()
                                , lEmployees.get(position).getLastName(), lEmployees.get(position).getGender(), lEmployees.get(position).getEmail(), lEmployees.get(position).getAddress(), lEmployees.get(position).getContactNo(), lEmployees.get(position).getSalary(), lEmployees.get(position).getCountryName());
                        in.putExtra(getString(R.string.bundle_emp_dto), employeeDTO);
                    } else  if (!"".equals(etSearch.getText().toString().trim())) {
                        EmployeeDTO employeeDTO = new EmployeeDTO(filteredList.get(position).getOrganization(), filteredList.get(position).getEmployeeID(), filteredList.get(position).getSubSubOrganizationID(), filteredList.get(position).getFirstName()
                                , filteredList.get(position).getLastName(), filteredList.get(position).getGender(), filteredList.get(position).getEmail(), filteredList.get(position).getAddress(), filteredList.get(position).getContactNo(), filteredList.get(position).getSalary(), filteredList.get(position).getCountryName());
                        in.putExtra(getString(R.string.bundle_emp_dto), employeeDTO);
                    }else{
                        EmployeeDTO employeeDTO = new EmployeeDTO(lEmployeesFiltered.get(position).getOrganization(), lEmployeesFiltered.get(position).getEmployeeID(), lEmployeesFiltered.get(position).getSubSubOrganizationID(), lEmployeesFiltered.get(position).getFirstName()
                                , lEmployeesFiltered.get(position).getLastName(), lEmployeesFiltered.get(position).getGender(), lEmployeesFiltered.get(position).getEmail(), lEmployeesFiltered.get(position).getAddress(), lEmployeesFiltered.get(position).getContactNo(), lEmployeesFiltered.get(position).getSalary(), lEmployeesFiltered.get(position).getCountryName());
                        in.putExtra(getString(R.string.bundle_emp_dto), employeeDTO);
                    }

                    startActivity(in);
                } else {
                    Intent in = new Intent(getActivity(), EmployeeDocumentActivity.class);
                    if ("".equals(etSearch.getText().toString().trim())) {
                        EmployeeDTO employeeDTO = new EmployeeDTO(lEmployees.get(position).getOrganization(), lEmployees.get(position).getEmployeeID(), lEmployees.get(position).getSubSubOrganizationID(), lEmployees.get(position).getFirstName()
                                , lEmployees.get(position).getLastName(), lEmployees.get(position).getGender(), lEmployees.get(position).getEmail(), lEmployees.get(position).getAddress(), lEmployees.get(position).getContactNo(), lEmployees.get(position).getSalary(), lEmployees.get(position).getCountryName());
                        in.putExtra(getString(R.string.bundle_emp_doc), employeeDTO);
                    } else {
                        EmployeeDTO employeeDTO = new EmployeeDTO(filteredList.get(position).getOrganization(), filteredList.get(position).getEmployeeID(), filteredList.get(position).getSubSubOrganizationID(), filteredList.get(position).getFirstName()
                                , filteredList.get(position).getLastName(), filteredList.get(position).getGender(), filteredList.get(position).getEmail(), filteredList.get(position).getAddress(), filteredList.get(position).getContactNo(), filteredList.get(position).getSalary(), filteredList.get(position).getCountryName());
                        in.putExtra(getString(R.string.bundle_emp_doc), employeeDTO);
                    }

                    startActivity(in);
                }

            }
        });

    }


    // Update UI on Main Thread
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventinMainThread(String compnayNmae) {
        lEmployeesFiltered.clear();
        etSearch.setText("");
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        if (compnayNmae.startsWith("All")) {
            compFilterApplied = false;
            mAdapter = new ListAdapter(lEmployees, getActivity(), "");
            lvList.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        } else {
            compFilterApplied = true;
            for (int i = 0; i < lEmployees.size(); i++) {
                if (compnayNmae.equals(lEmployees.get(i).getOrganization())) {
                    lEmployeesFiltered.add(lEmployees.get(i));
                }
            }
            mAdapter = new ListAdapter(lEmployeesFiltered, getActivity(), "");
            lvList.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }


    @Override
    public void onSuccess(ResponseDTO responseDTO) {
        switch (responseDTO.getCallBackId()) {

            case SystemConstants.RESPONSE_EMPLOYEES:
                EmployeeResponseDTO employeeResponseDTO = (EmployeeResponseDTO) responseDTO;
                if (responseDTO != null) {
                    if (null == responseDTO.getMessage()) {
                        CommonActions.DismissesDialog();
                        lEmployees = employeeResponseDTO.getEmployeeDTO();
                        mAdapter = new ListAdapter(lEmployees, getActivity(), "");
                        lvList.setAdapter(mAdapter);
                        BaseActivity.companisList.clear();
                        for (int i = 0; i < lEmployees.size(); i++) {
                            if (!"".equals(lEmployees.get(i).getOrganization())) {
                                if (!BaseActivity.companisList.contains(lEmployees.get(i).getOrganization())) {
                                    BaseActivity.companisList.add(lEmployees.get(i).getOrganization());
                                }
                            }
                        }
                    } else {
                        CommonActions.DismissesDialog();
                        new AlertDialog.Builder(getActivity())
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
            Toast.makeText(getActivity(), R.string.error_404_msg, Toast.LENGTH_LONG).show();
        else if (1 == MyApplication.getInstance().getStatusCode())
            Toast.makeText(getActivity(), R.string.error_poor_con, Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getActivity(), R.string.error_con_timeout, Toast.LENGTH_LONG).show();
    }
}
