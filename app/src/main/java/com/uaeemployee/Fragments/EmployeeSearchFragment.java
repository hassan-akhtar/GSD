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

import java.util.ArrayList;
import java.util.List;

public class EmployeeSearchFragment extends Fragment implements MyCallBack{

    View mView;
    List<EmployeeDTO> lEmployees = new ArrayList<EmployeeDTO>();
    List<EmployeeDTO> filteredList = new ArrayList<EmployeeDTO>();
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
        getAllEmployees();

        if (lEmployees.size() < 0)
            tvNoTextFound.setVisibility(View.VISIBLE);
        else
            tvNoTextFound.setVisibility(View.GONE);

        return mView;
    }

    private void getAllEmployees() {
        CommonActions.showProgressDialog(getActivity());
        GSDServiceFactory.getService(getActivity()).getEmployees(new com.uaeemployee.Network.RequestDTOs.VacanciesDTO(SystemConstants.RESPONSE_EMPLOYEES,1,1,MyApplication.getInstance().getUserID()),this);
    }

    private void initViews() {
        etSearch = (EditText) mView.findViewById(R.id.etSearch);
        lvList = (ListView) mView.findViewById(R.id.lvList);
        tvNoTextFound = (TextView) mView.findViewById(R.id.tvNoTextFound);
        toolbar = (Toolbar) mView.findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);
        if (!BaseActivity.isEmployeeDoc ) {
            ((BaseActivity) getActivity()).mToolbar.setTitle(getString(R.string.employeeSearch));
        } else {
            ((BaseActivity) getActivity()).mToolbar.setTitle(getString(R.string.employee_document));
        }
    }

    private void initObj() {
        BaseActivity.fragment = new EmployeeSearchFragment();
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

                    final String name = lEmployees.get(i).getFirstName().toLowerCase();
                    if (name.startsWith((String) cs)) {

                        filteredList.add(lEmployees.get(i));
                    }
                }

                if (0 == filteredList.size()) {
                    tvNoTextFound.setVisibility(View.VISIBLE);
                    mAdapter = new ListAdapter(filteredList, getActivity(),"");
                    lvList.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                } else {
                    tvNoTextFound.setVisibility(View.GONE);
                    mAdapter = new ListAdapter(filteredList, getActivity(),"");
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


        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!BaseActivity.isEmployeeDoc ) {
                    Intent in = new Intent(getActivity(), EmployeeProfileActivity.class);
                    if("".equals(etSearch.getText().toString().trim())){
                        EmployeeDTO employeeDTO = new EmployeeDTO(lEmployees.get(position).getEmployeeID(), lEmployees.get(position).getSubSubOrganizationID(), lEmployees.get(position).getFirstName()
                                , lEmployees.get(position).getLastName(), lEmployees.get(position).getGender(), lEmployees.get(position).getEmail(), lEmployees.get(position).getAddress(), lEmployees.get(position).getContactNo(), lEmployees.get(position).getSalary(), lEmployees.get(position).getCountryName());
                        in.putExtra(getString(R.string.bundle_emp_dto), employeeDTO);
                    }else{
                        EmployeeDTO employeeDTO = new EmployeeDTO(filteredList.get(position).getEmployeeID(), filteredList.get(position).getSubSubOrganizationID(), filteredList.get(position).getFirstName()
                                , filteredList.get(position).getLastName(), filteredList.get(position).getGender(), filteredList.get(position).getEmail(), filteredList.get(position).getAddress(), filteredList.get(position).getContactNo(), filteredList.get(position).getSalary(), filteredList.get(position).getCountryName());
                        in.putExtra(getString(R.string.bundle_emp_dto), employeeDTO);
                    }

                    startActivity(in);
                } else {
                    Intent in = new Intent(getActivity(), EmployeeDocumentActivity.class);
                    if("".equals(etSearch.getText().toString().trim())){
                        EmployeeDTO employeeDTO = new EmployeeDTO(lEmployees.get(position).getEmployeeID(), lEmployees.get(position).getSubSubOrganizationID(), lEmployees.get(position).getFirstName()
                                , lEmployees.get(position).getLastName(), lEmployees.get(position).getGender(), lEmployees.get(position).getEmail(), lEmployees.get(position).getAddress(), lEmployees.get(position).getContactNo(), lEmployees.get(position).getSalary(), lEmployees.get(position).getCountryName());
                        in.putExtra(getString(R.string.bundle_emp_doc), employeeDTO);
                    }else{
                        EmployeeDTO employeeDTO = new EmployeeDTO(filteredList.get(position).getEmployeeID(), filteredList.get(position).getSubSubOrganizationID(), filteredList.get(position).getFirstName()
                                , filteredList.get(position).getLastName(), filteredList.get(position).getGender(), filteredList.get(position).getEmail(), filteredList.get(position).getAddress(), filteredList.get(position).getContactNo(), filteredList.get(position).getSalary(), filteredList.get(position).getCountryName());
                        in.putExtra(getString(R.string.bundle_emp_doc), employeeDTO);
                    }

                    startActivity(in);
                }

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
                        mAdapter = new ListAdapter(lEmployees, getActivity(),"");
                        lvList.setAdapter(mAdapter);
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
