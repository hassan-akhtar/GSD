package com.uaeemployee.Activites;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.uaeemployee.Adapters.OrganizationAdapter;
import com.uaeemployee.Application.MyApplication;
import com.uaeemployee.Network.RequestDTOs.GenderRequestDTO;
import com.uaeemployee.Network.ResponseDTOs.GenderResponseDTO;
import com.uaeemployee.Network.ResponseDTOs.NationDTO;
import com.uaeemployee.Network.ResponseDTOs.NationDTOList;
import com.uaeemployee.Network.ResponseDTOs.OrganizationsResponseDTO;
import com.uaeemployee.Network.ResponseDTOs.ResponseDTO;
import com.uaeemployee.Network.Service.GSDServiceFactory;
import com.uaeemployee.Network.Service.MyCallBack;
import com.uaeemployee.R;
import com.uaeemployee.Utils.CommonActions;
import com.uaeemployee.Utils.SharedPreferencesManager;
import com.uaeemployee.Utils.SystemConstants;

import java.util.ArrayList;
import java.util.List;

public class GenderActivity extends AppCompatActivity implements MyCallBack {

    private Toolbar mToolbar;
    RelativeLayout llMale, llFemale, llLocal;
    SharedPreferencesManager sharedpreferences;
    TextView tvLocalCount, tvMaleCount, tvFemalecount, tvNoTextFound, tvName;
    int organizationID, orgType, maleCount, femaleCount;
    boolean maleDisabled = false, femaleDisabled = false;
    ImageView ivForwardArrow;
    List<NationDTO>  lstNationMale  = new ArrayList<NationDTO>();
    List<NationDTO> lstNationFemale   = new ArrayList<NationDTO>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);

        initViews();
        initObj();
        initListeners();
        getGenderCall();

    }


    private void initViews() {

        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        llMale = (RelativeLayout) findViewById(R.id.llMale);
        llFemale = (RelativeLayout) findViewById(R.id.llFemale);
        llLocal = (RelativeLayout) findViewById(R.id.llLocal);
        tvLocalCount = (TextView) findViewById(R.id.tvLocalCount);
        tvMaleCount = (TextView) findViewById(R.id.tvMaleCount);
        tvNoTextFound  = (TextView) findViewById(R.id.tvNoTextFound);
        tvFemalecount = (TextView) findViewById(R.id.tvFemaleCount);
        tvName = (TextView) findViewById(R.id.tvName);
        ivForwardArrow = (ImageView) findViewById(R.id.ivForwardArrow);
        ivForwardArrow.setVisibility(View.GONE);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

    }

    private void initObj() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbar.setNavigationIcon(R.drawable.back_icon);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setTitle(R.string.txt_select_gender);
        sharedpreferences = new SharedPreferencesManager(GenderActivity.this);

        organizationID = getIntent().getIntExtra("org_Id", 0);
        orgType = getIntent().getIntExtra("org_type", 0);
    }

    private void initListeners() {
        llMale.setOnClickListener(mGlobal_OnClickListener);
        llFemale.setOnClickListener(mGlobal_OnClickListener);
        llLocal.setOnClickListener(mGlobal_OnClickListener);
    }


    private void getGenderCall() {
        CommonActions.showProgressDialog(GenderActivity.this);
        GSDServiceFactory.getService(getApplicationContext()).GetCountByGender(new GenderRequestDTO(SystemConstants.RESPONSE_GENDER, organizationID, orgType, MyApplication.getInstance().getUserID()), this);
    }


    final View.OnClickListener mGlobal_OnClickListener = new View.OnClickListener() {
        public void onClick(final View v) {
            switch (v.getId()) {
                case R.id.llMale: {
                    Intent in  = new Intent(GenderActivity.this, NationalityActivity.class);
                    in.putExtra("List", new NationDTOList(lstNationMale));
                    in.putExtra("org_Id",organizationID);
                    in.putExtra("org_type",orgType);
                    in.putExtra("gender_type",1);
                    startActivity(in);
                    sharedpreferences.setString(SharedPreferencesManager.CURRENT_GENDER, getString(R.string.txt_male), GenderActivity.this);
                    break;
                }

                case R.id.llFemale: {
                    Intent in  = new Intent(GenderActivity.this, NationalityActivity.class);
                    in.putExtra("List", new NationDTOList(lstNationFemale));
                    in.putExtra("org_Id",organizationID);
                    in.putExtra("org_type",orgType);
                    in.putExtra("gender_type",0);
                    startActivity(in);
                    sharedpreferences.setString(SharedPreferencesManager.CURRENT_GENDER, getString(R.string.txt_female), GenderActivity.this);
                    break;
                }

                case R.id.llLocal: {
                    startActivity(new Intent(GenderActivity.this, EmployeeSearchActivity.class));
                    sharedpreferences.setString(SharedPreferencesManager.CURRENT_NATIONALITY, getString(R.string.txt_local_only), GenderActivity.this);
                    break;
                }

            }
        }

    };

    @Override
    public void onSuccess(ResponseDTO responseDTO) {
        switch (responseDTO.getCallBackId()) {

            case SystemConstants.RESPONSE_GENDER:
                GenderResponseDTO genderResponseDTO = (GenderResponseDTO) responseDTO;
                if (responseDTO != null) {
                    if (null == responseDTO.getMessage()) {
                        CommonActions.DismissesDialog();
                        lstNationFemale.clear();
                        lstNationMale.clear();
                        int salary = 0;
                        if (0 < genderResponseDTO.getGenderDTO().size()) {
                            for (int i = 0; i < genderResponseDTO.getGenderDTO().size(); i++) {
                                String gender = genderResponseDTO.getGenderDTO().get(i).getGen();
                                if ("Female".equals(gender)) {
                                    femaleCount = genderResponseDTO.getGenderDTO().get(i).getCount();
                                    tvFemalecount.setText("" + femaleCount);
                                    salary = salary+ genderResponseDTO.getGenderDTO().get(i).getSalary();
                                    llFemale.setVisibility(View.VISIBLE);
                                    lstNationFemale.addAll(genderResponseDTO.getGenderDTO().get(i).getLstNation());
                                } else if ("Male".equals(gender)) {
                                    maleCount = genderResponseDTO.getGenderDTO().get(i).getCount();
                                    tvMaleCount.setText("" + maleCount);
                                    salary = salary+ genderResponseDTO.getGenderDTO().get(i).getSalary();
                                    lstNationMale.addAll(genderResponseDTO.getGenderDTO().get(i).getLstNation());
                                    llMale.setVisibility(View.VISIBLE);
                                }
                            }
                            tvName.setText("Total Salary: "+salary);
                        }

                        if(llFemale.getVisibility()==View.GONE && llMale.getVisibility()==View.GONE ){
                            tvNoTextFound.setVisibility(View.VISIBLE);
                        }
                        else {
                            tvNoTextFound.setVisibility(View.GONE);
                        }
                    } else {
                        CommonActions.DismissesDialog();
                        new AlertDialog.Builder(getApplicationContext())
                                .setTitle(getString(R.string.app_name))
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
