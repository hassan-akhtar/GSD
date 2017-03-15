package com.uaeemployee.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.uaeemployee.Activites.BaseActivity;
import com.uaeemployee.Activites.GenderActivity;
import com.uaeemployee.Adapters.SubOrganizationAdapter;
import com.uaeemployee.Adapters.SubSubOrganizationAdapter;
import com.uaeemployee.Network.RequestDTOs.GenderRequestDTO;
import com.uaeemployee.Network.ResponseDTOs.GenderDTO;
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
    RelativeLayout lHeader;
    SubOrganizationAdapter adapter;
    SubSubOrganizationAdapter adapter2;
    View mView;
    TextView tvName, tvMaleCount, tvFemaleCount, tvLocalCount, tvNoTextFound;
    public static List<SubSubOrganizationsDTO> subSubOrganizationsDTO;
    List<SubOrganizationsDTO> subOrganizationsDTO;
    boolean isSubSub = false;
    private static int OrganizationID = 0 ;


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
                    tvName.setText(subOrganizationsDTO.get(position).getName());
                    adapter2 = new SubSubOrganizationAdapter(subSubOrganizationsDTO, getActivity());
                    lvSubOrgs.setAdapter(adapter2);
                    isSubSub = true;
                    OrganizationID = subOrganizationsDTO.get(position).getSubOrgnizationID();

                    if(0 == subOrganizationsDTO.size()){
                        tvNoTextFound.setVisibility(View.VISIBLE);
                    }
                } else {
                    Intent intent = new Intent(getActivity(), GenderActivity.class);
                    intent.putExtra("org_Id", subSubOrganizationsDTO.get(position).getSubSubOrgnizationID());
                    intent.putExtra("org_type", 3);
                    startActivity(intent);
                }
            }
        });

        lHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int orgType = 0;
                Intent intent = new Intent(getActivity(), GenderActivity.class);
                intent.putExtra("org_Id", OrganizationID);
                if (!isSubSub) {
                    orgType = 1;
                }else{
                    orgType = 2;
                }
                intent.putExtra("org_type", orgType);
                startActivity(intent);
            }
        });
    }

    private void initViews() {

        lvSubOrgs = (ListView) mView.findViewById(R.id.lvSubOrgs);
        CommonActions.showProgressDialog(getActivity());
        tvNoTextFound  = (TextView) mView.findViewById(R.id.tvNoTextFound);
        lHeader  = (RelativeLayout) mView.findViewById(R.id.lHeader);
        tvName = (TextView) mView.findViewById(R.id.tvName);
        tvMaleCount = (TextView) mView.findViewById(R.id.tvMaleCount);
        tvFemaleCount = (TextView) mView.findViewById(R.id.tvFemaleCount);
        tvLocalCount = (TextView) mView.findViewById(R.id.tvLocalCount);
        ((BaseActivity) getActivity()).mToolbar.setTitle(getString(R.string.suborganizations));
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
        if(0 == subOrganizationsDTO.size()){
            tvNoTextFound.setVisibility(View.VISIBLE);
        }
    }

    // Update UI on Main Thread
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventinMainThread(String groupName) {
        String[] str = groupName.split(",");
        tvName.setText(str[0]);
        OrganizationID = Integer.parseInt(str[1]);

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
