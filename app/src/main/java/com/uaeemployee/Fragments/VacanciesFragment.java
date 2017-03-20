package com.uaeemployee.Fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.uaeemployee.Activites.BaseActivity;
import com.uaeemployee.Activites.EmployeeProfileActivity;
import com.uaeemployee.Activites.VacancyDetailActivity;
import com.uaeemployee.Adapters.ListAdapter;
import com.uaeemployee.Adapters.OrganizationAdapter;
import com.uaeemployee.Application.MyApplication;
import com.uaeemployee.Models.Employee;
import com.uaeemployee.Network.ResponseDTOs.OrganizationsResponseDTO;
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

public class VacanciesFragment extends Fragment implements MyCallBack {

    View mView;

    List<VacanciesDTO> lVacanciesFiltered = new ArrayList<VacanciesDTO>();
    List<VacanciesDTO> lVacancies = new ArrayList<VacanciesDTO>();
    List<VacanciesDTO> filteredList = new ArrayList<VacanciesDTO>();
    EditText etSearch;
    ListView lvList;
    ListAdapter mAdapter;
    boolean compFilterApplied = false;
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
        //populateListVacancies();
        getAllVacancies();

        if (lVacancies.size() < 0)
            tvNoTextFound.setVisibility(View.VISIBLE);
        else
            tvNoTextFound.setVisibility(View.GONE);

        return mView;
    }

    private void getAllVacancies() {
        CommonActions.showProgressDialog(getActivity());
        GSDServiceFactory.getService(getActivity()).getVacancies(new com.uaeemployee.Network.RequestDTOs.VacanciesDTO(SystemConstants.RESPONSE_VACANCIES,MyApplication.getInstance().getUserID()), this);
    }

    private void initViews() {
        etSearch = (EditText) mView.findViewById(R.id.etSearch);
        lvList = (ListView) mView.findViewById(R.id.lvList);
        tvNoTextFound = (TextView) mView.findViewById(R.id.tvNoTextFound);
        ((BaseActivity) getActivity()).mToolbar.setTitle(getString(R.string.vacancies));
    }

    private void initObj() {
        BaseActivity.fragment = new VacanciesFragment();
        sharedPreferencesManager = new SharedPreferencesManager(getActivity());
        sharedPreferencesManager.setBoolean(SharedPreferencesManager.IS_VACANCY, true, getActivity());
    }

    private void initListeners() {

        etSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                cs = cs.toString().toLowerCase();

                filteredList.clear();

                if (!compFilterApplied) {
                    for (int i = 0; i < lVacancies.size(); i++) {

                        final String name = lVacancies.get(i).getTitle().toLowerCase();
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
                } else {
                    for (int i = 0; i < lVacanciesFiltered.size(); i++) {

                        final String name = lVacanciesFiltered.get(i).getTitle().toLowerCase();
                        if (name.startsWith((String) cs)) {

                            filteredList.add(lVacanciesFiltered.get(i));
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
                getActivity().getSupportFragmentManager().executePendingTransactions();
                Intent in = new Intent(getActivity(), VacancyDetailActivity.class);
                if ("".equals(etSearch.getText().toString().trim())&& !compFilterApplied) {
                    VacanciesDTO vacanciesDTO = new VacanciesDTO(lVacancies.get(position).getOrganization(),lVacancies.get(position).getVacancyID(), lVacancies.get(position).getJobType(), lVacancies.get(position).getTitle()
                            , lVacancies.get(position).getJobLevel(), lVacancies.get(position).getDescription(), lVacancies.get(position).getSubSubOrganizationID());
                    in.putExtra(getString(R.string.bundle_vacDto), vacanciesDTO);
                } else  if (!"".equals(etSearch.getText().toString().trim())) {
                    VacanciesDTO vacanciesDTO = new VacanciesDTO(filteredList.get(position).getOrganization(),filteredList.get(position).getVacancyID(), filteredList.get(position).getJobType(), filteredList.get(position).getTitle()
                            , filteredList.get(position).getJobLevel(), filteredList.get(position).getDescription(), filteredList.get(position).getSubSubOrganizationID());
                    in.putExtra(getString(R.string.bundle_vacDto), vacanciesDTO);
                }else{
                    VacanciesDTO vacanciesDTO = new VacanciesDTO(lVacanciesFiltered.get(position).getOrganization(),
                            lVacanciesFiltered.get(position).getVacancyID(), lVacanciesFiltered.get(position).getJobType(), lVacanciesFiltered.get(position).getTitle()
                            , lVacanciesFiltered.get(position).getJobLevel(), lVacanciesFiltered.get(position).getDescription(), lVacanciesFiltered.get(position).getSubSubOrganizationID());
                    in.putExtra(getString(R.string.bundle_vacDto), vacanciesDTO);
                }

                startActivity(in);

            }
        });
    }


    // Update UI on Main Thread
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventinMainThread(String compnayNmae) {
        etSearch.setText("");
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        lVacanciesFiltered.clear();
        if (compnayNmae.startsWith("All")) {
            compFilterApplied = false;
            mAdapter = new ListAdapter(lVacancies, getActivity());
            lvList.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        } else {
            compFilterApplied = true;
            for (int i = 0; i < lVacancies.size(); i++) {
                if (compnayNmae.equals(lVacancies.get(i).getOrganization())) {
                    lVacanciesFiltered.add(lVacancies.get(i));
                }
            }
            mAdapter = new ListAdapter(lVacanciesFiltered, getActivity());
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

            case SystemConstants.RESPONSE_VACANCIES:
                VacanciesResponseDTO vacanciesResponseDTO = (VacanciesResponseDTO) responseDTO;
                if (responseDTO != null) {
                    if (null == responseDTO.getMessage()) {
                        CommonActions.DismissesDialog();
                        lVacancies = vacanciesResponseDTO.getVacanciesDTO();
                        mAdapter = new ListAdapter(lVacancies, getActivity());
                        lvList.setAdapter(mAdapter);
                        BaseActivity.companisList.clear();
                        for (int i=0;i<lVacancies.size();i++){
                            if (!"".equals(lVacancies.get(i).getOrganization())) {
                                if (!BaseActivity.companisList.contains(lVacancies.get(i).getOrganization())) {
                                    BaseActivity.companisList.add(lVacancies.get(i).getOrganization());
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
