package com.uaeemployee.Fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.uaeemployee.Activites.BaseActivity;
import com.uaeemployee.Adapters.OrganizationAdapter;
import com.uaeemployee.Application.MyApplication;
import com.uaeemployee.Interfaces.Communicator;
import com.uaeemployee.Network.ResponseDTOs.OrganizationsDTO;
import com.uaeemployee.Network.ResponseDTOs.OrganizationsResponseDTO;
import com.uaeemployee.Network.ResponseDTOs.ResponseDTO;
import com.uaeemployee.Network.Service.GSDServiceFactory;
import com.uaeemployee.Network.Service.MyCallBack;
import com.uaeemployee.R;
import com.uaeemployee.Utils.CommonActions;
import com.uaeemployee.Utils.SystemConstants;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


public class OrganizationFragment extends Fragment implements MyCallBack {

    View mView;
    Communicator myCommunicator;
    ListView lvOrgs;
    OrganizationAdapter adapter;
    private List<OrganizationsDTO> organizationsDTO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_org, container, false);
        initViews();
        initObj();
        initListeners();
        getAllOrganizations();
        return mView;
    }

    private void getAllOrganizations() {
        CommonActions.showProgressDialog(getActivity());
        GSDServiceFactory.getService(getActivity()).getOrganizations(new com.uaeemployee.Network.RequestDTOs.OrganizationsDTO(SystemConstants.RESPONSE_ORGANIZATIONS,1),this);
    }

    private void initListeners() {

        lvOrgs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BaseActivity.refreshMainViewByNew(new SubSubOrganizationFragment());
                getActivity().getSupportFragmentManager().executePendingTransactions();
                EventBus.getDefault().post(organizationsDTO.get(position).getLstSubOrganization());
                if (SubSubOrganizationFragment.subSubOrganizationsDTO!=null) {
                    SubSubOrganizationFragment.subSubOrganizationsDTO.clear();
                }
                SubSubOrganizationFragment.subSubOrganizationsDTO = organizationsDTO.get(position).getLstSubSubOrganization();
            }
        });
    }

    private void initViews() {
        lvOrgs = (ListView) mView.findViewById(R.id.lvOrgs);
        ((BaseActivity) getActivity()).mToolbar.setTitle(getString(R.string.organizations));
    }

    private void initObj() {
        myCommunicator = (Communicator) getActivity();
        BaseActivity.fragment = new OrganizationFragment();
    }


    @Override
    public void onSuccess(ResponseDTO responseDTO) {
        switch (responseDTO.getCallBackId()) {

            case SystemConstants.RESPONSE_ORGANIZATIONS:
                OrganizationsResponseDTO organizationsResponseDTO = (OrganizationsResponseDTO) responseDTO;
                if (responseDTO != null) {
                    if (null==responseDTO.getMessage()) {
                        CommonActions.DismissesDialog();
                        organizationsDTO =organizationsResponseDTO.getOrganizationsDTO();
                        adapter = new OrganizationAdapter(organizationsDTO, getActivity());
                        lvOrgs.setAdapter(adapter);
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
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
