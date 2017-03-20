package com.uaeemployee.Activites;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.uaeemployee.Adapters.EmployeeDocumentAdapter;
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

import java.util.List;

public class EmployeeDocumentActivity extends AppCompatActivity implements MyCallBack {

    TextView tvEmployeeID, tvDocumentName, tvNoTextFound;
    SharedPreferencesManager sharedPreferencesManager;
    private Toolbar mToolbar;
    EmployeeDTO employeeDTO;
    EmployeeDocumentAdapter employeeDocumentAdapter;
    List<EmployeeDocument> employeeDocumentList;
    ListView lvEmployee;


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
        tvNoTextFound = (TextView) findViewById(R.id.tvNoTextFound);
        lvEmployee = (ListView) findViewById(R.id.lvList);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        tvNoTextFound.setVisibility(View.GONE);
    }

    private void initObj() {
        sharedPreferencesManager = new SharedPreferencesManager(EmployeeDocumentActivity.this);
        employeeDTO = (EmployeeDTO) getIntent().getSerializableExtra(getString(R.string.bundle_employee_doc));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.txt_employee_doc));
        mToolbar.setNavigationIcon(R.drawable.back_icon);
    }

    private void getEmployeeDocument() {
        CommonActions.showProgressDialog(EmployeeDocumentActivity.this);
        GSDServiceFactory.getService(getApplicationContext()).getEmployeeDocument(new com.uaeemployee.Network.RequestDTOs.OrganizationsDTO(SystemConstants.RESPONSE_EMPLOYEES_DOC, employeeDTO.getEmployeeID()), this);
    }

    private void initListeners() {

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        lvEmployee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if ("".equals(employeeDocumentList.get(position).getDocPath())) {
                    Toast.makeText(getApplicationContext(), getString(R.string.txt_doc_path_new), Toast.LENGTH_LONG).show();
                } else {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(employeeDocumentList.get(position).getDocPath()));
                    startActivity(browserIntent);
                }
            }
        });
    }

    @Override
    public void onSuccess(ResponseDTO responseDTO) {
        switch (responseDTO.getCallBackId()) {

            case SystemConstants.RESPONSE_EMPLOYEES_DOC:
                EmployeeDocumentResponseDTO employeeResponseDTO = (EmployeeDocumentResponseDTO) responseDTO;
                if (responseDTO != null) {
                    if (null == responseDTO.getMessage()) {
                        CommonActions.DismissesDialog();
                        employeeDocumentList = employeeResponseDTO.getEmployeeDocument();
                        setupData();

                    } else {
                        CommonActions.DismissesDialog();
                        new AlertDialog.Builder(getApplicationContext())
                                .setTitle(getString(R.string.txt_employee_doc))
                                .setMessage(responseDTO.getMessage())
                                .setNegativeButton(R.string.txt_close, new DialogInterface.OnClickListener() {
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

    private void setupData() {
        if (0 != employeeDocumentList.size()) {
            tvNoTextFound.setVisibility(View.GONE);
            employeeDocumentAdapter = new EmployeeDocumentAdapter(employeeDocumentList, getApplicationContext());
            lvEmployee.setAdapter(employeeDocumentAdapter);
        } else {
            tvNoTextFound.setVisibility(View.VISIBLE);
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