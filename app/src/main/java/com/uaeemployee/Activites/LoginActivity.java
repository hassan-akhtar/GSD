package com.uaeemployee.Activites;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.uaeemployee.Application.MyApplication;
import com.uaeemployee.Network.RequestDTOs.LoginDTO;
import com.uaeemployee.Network.ResponseDTOs.LoginResponseDTO;
import com.uaeemployee.Network.ResponseDTOs.ResponseDTO;
import com.uaeemployee.Network.Service.GSDServiceFactory;
import com.uaeemployee.Network.Service.MyCallBack;
import com.uaeemployee.R;
import com.uaeemployee.Utils.CommonActions;
import com.uaeemployee.Utils.SystemConstants;

public class LoginActivity extends AppCompatActivity   implements MyCallBack {

    Button btnLogin;
    EditText etUser, etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        initObj();
        initListeners();

    }

    private void initViews() {
        btnLogin = (Button) findViewById(R.id.btnLogin);
        etUser = (EditText) findViewById(R.id.etUser);
        etPass = (EditText) findViewById(R.id.etPass);
    }

    private void initObj() {
    }

    private void initListeners() {
        btnLogin.setOnClickListener(mGlobal_OnClickListener);
    }

    final View.OnClickListener mGlobal_OnClickListener = new View.OnClickListener() {
        public void onClick(final View v) {
            switch (v.getId()) {
                case R.id.btnLogin: {
                    if (checkValidation()) {
                       callLoginService();
                    }
                    break;
                }
            }
        }

    };

    void callLoginService(){
        CommonActions.showProgressDialog(LoginActivity.this);
        GSDServiceFactory.getService(LoginActivity.this).loginRequest(new LoginDTO(SystemConstants.RESPONSE_LOGIN,etUser.getText().toString(),etPass.getText().toString()),this);
    }

    private boolean checkValidation() {

        if ("".equals(etUser.getText().toString().trim())) {
            showToast("Please enter username");
        } else if ("".equals(etPass.getText().toString().trim())) {
            showToast("Please enter password");
        } else {
            return true;
        }
        return false;
    }

    void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(ResponseDTO responseDTO) {
        switch (responseDTO.getCallBackId()) {

            case SystemConstants.RESPONSE_LOGIN:
                LoginResponseDTO loginResponseDTO = (LoginResponseDTO) responseDTO;
                if (responseDTO != null) {
                    if (0!=loginResponseDTO.getUserID()) {
                        CommonActions.DismissesDialog();
                        startActivity(new Intent(LoginActivity.this, BaseActivity.class));
                        finish();
                    } else {
                        CommonActions.DismissesDialog();
                        new AlertDialog.Builder(LoginActivity.this)
                                .setTitle("Login")
                                .setMessage("Login Failed. Please try again")
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
