package com.uaeemployee.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.uaeemployee.Activites.BaseActivity;
import com.uaeemployee.Adapters.subOrganizationAdapter;
import com.uaeemployee.Network.ResponseDTOs.SubSubOrganizationsDTO;
import com.uaeemployee.R;
import com.uaeemployee.Utils.CommonActions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class SubSubOrganizationFragment extends Fragment {

    ListView lvSubOrgs;
    subOrganizationAdapter adapter;
    View mView;
    List<SubSubOrganizationsDTO> subOrganizationsDTO;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_sub_sub_organization, container, false);
        initViews();
        initObj();
        initListeners();
        return mView;
    }

    private void initListeners() {

        lvSubOrgs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),"Clicked: "+position, Toast.LENGTH_LONG).show();
                //EventBus.getDefault().post(subOrganizationsDTO.get(position));
            }
        });
    }

    private void initViews() {
        lvSubOrgs = (ListView) mView.findViewById(R.id.lvSubOrgs);
        CommonActions.showProgressDialog(getActivity());
    }

    private void initObj() {

        BaseActivity.fragment = new SubSubOrganizationFragment();


    }

    // Update UI on Main Thread
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventinMainThread(List<SubSubOrganizationsDTO> subOrganizationsDTO) {
        CommonActions.DismissesDialog();
        this.subOrganizationsDTO = subOrganizationsDTO;
        adapter = new subOrganizationAdapter(subOrganizationsDTO, getActivity());
        lvSubOrgs.setAdapter(adapter);

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
}
