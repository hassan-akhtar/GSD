package com.uaeemployee.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.uaeemployee.Activites.BaseActivity;
import com.uaeemployee.Activites.GenderActivity;
import com.uaeemployee.Adapters.SubOrganizationAdapter;
import com.uaeemployee.Adapters.SubSubOrganizationAdapter;
import com.uaeemployee.Network.ResponseDTOs.SubOrganizationsDTO;
import com.uaeemployee.Network.ResponseDTOs.SubSubOrganizationsDTO;
import com.uaeemployee.R;
import com.uaeemployee.Utils.CommonActions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class SubSubOrganizationFragment extends Fragment {

    ListView lvSubOrgs;
    SubOrganizationAdapter adapter;
    SubSubOrganizationAdapter adapter2;
    View mView;
    public static List<SubSubOrganizationsDTO> subSubOrganizationsDTO;
    List<SubOrganizationsDTO> subOrganizationsDTO;
    boolean isSubSub = false;


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
                if (!isSubSub) {
                    adapter2 = new SubSubOrganizationAdapter(subSubOrganizationsDTO,getActivity());
                    lvSubOrgs.setAdapter(adapter2);
                    isSubSub = true;
                }else {
                    startActivity(new Intent(getActivity(), GenderActivity.class));
                }
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
    public void onMessageEventinMainThread(List<SubOrganizationsDTO> SubOrganizationsDTO) {
        CommonActions.DismissesDialog();
        this.subOrganizationsDTO = SubOrganizationsDTO;
        adapter = new SubOrganizationAdapter(SubOrganizationsDTO, getActivity());
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
