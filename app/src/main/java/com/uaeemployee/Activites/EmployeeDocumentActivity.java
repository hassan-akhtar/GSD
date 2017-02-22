package com.uaeemployee.Activites;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.uaeemployee.Adapters.ListAdapter;
import com.uaeemployee.Application.MyApplication;
import com.uaeemployee.Network.ResponseDTOs.EmployeeDTO;
import com.uaeemployee.Network.ResponseDTOs.EmployeeDocument;
import com.uaeemployee.Network.ResponseDTOs.EmployeeDocumentResponseDTO;
import com.uaeemployee.Network.ResponseDTOs.EmployeeResponseDTO;
import com.uaeemployee.Network.ResponseDTOs.ResponseDTO;
import com.uaeemployee.Network.Service.GSDServiceFactory;
import com.uaeemployee.Network.Service.MyCallBack;
import com.uaeemployee.R;
import com.uaeemployee.Utils.CommonActions;
import com.uaeemployee.Utils.SharedPreferencesManager;
import com.uaeemployee.Utils.SystemConstants;

public class EmployeeDocumentActivity extends AppCompatActivity implements MyCallBack{

    TextView tvEmployeeID,tvDocumentName;
    SharedPreferencesManager sharedPreferencesManager;
    private Toolbar mToolbar;
    EmployeeDTO employeeDTO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_document);
        initViews();
        initObj();
        initListeners();
        getEmployeeDocument();
    }

    private void initViews() {
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        tvEmployeeID = (TextView) findViewById(R.id.tvEmployeeID);
        tvDocumentName = (TextView) findViewById(R.id.tvDocumentName);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void initObj() {
        sharedPreferencesManager = new SharedPreferencesManager(EmployeeDocumentActivity.this);
        employeeDTO = (EmployeeDTO) getIntent().getSerializableExtra("employee_doc_DTO_Obj");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Employee Document");
        mToolbar.setNavigationIcon(R.drawable.back_icon);
    }

    private void getEmployeeDocument() {
        CommonActions.showProgressDialog(EmployeeDocumentActivity.this);
        GSDServiceFactory.getService(getApplicationContext()).getEmployeeDocument(new com.uaeemployee.Network.RequestDTOs.OrganizationsDTO(SystemConstants.RESPONSE_EMPLOYEES_DOC,employeeDTO.getEmployeeID()),this);
    }
    private void initListeners() {

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    @Override
    public void onSuccess(ResponseDTO responseDTO) {
        switch (responseDTO.getCallBackId()) {

            case SystemConstants.RESPONSE_EMPLOYEES_DOC:
                EmployeeDocumentResponseDTO employeeResponseDTO = (EmployeeDocumentResponseDTO) responseDTO;
                if (responseDTO != null) {
                    if (null==responseDTO.getMessage()) {
                        CommonActions.DismissesDialog();
                        // Employee k document ki list aae hai
                        //etupData();
                        Toast.makeText(getApplicationContext(),"recvd",Toast.LENGTH_LONG).show();

                    } else {
                        CommonActions.DismissesDialog();
                        new AlertDialog.Builder(getApplicationContext())
                                .setTitle("Employee Documents")
                                .setMessage(responseDTO.getMessage())
                                .setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
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
            Toast.makeText(getApplicationContext(), "Service is not Available please try again Later!", Toast.LENGTH_LONG).show();
        else if (1 == MyApplication.getInstance().getStatusCode())
            Toast.makeText(getApplicationContext(), "Poor or no Internet Connection!", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(), "Connection Timeout!", Toast.LENGTH_LONG).show();
    }
}