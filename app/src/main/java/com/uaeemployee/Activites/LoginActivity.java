package com.uaeemployee.Activites;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.uaeemployee.Application.MyApplication;
import com.uaeemployee.Network.RequestDTOs.LoginDTO;
import com.uaeemployee.Network.ResponseDTOs.LoginResponseDTO;
import com.uaeemployee.Network.ResponseDTOs.ResponseDTO;
import com.uaeemployee.Network.Service.GSDServiceFactory;
import com.uaeemployee.Network.Service.MyCallBack;
import com.uaeemployee.R;
import com.uaeemployee.Utils.CommonActions;
import com.uaeemployee.Utils.SharedPreferencesManager;
import com.uaeemployee.Utils.SystemConstants;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity   implements MyCallBack {

    Button btnLogin;
    EditText etUser, etPass;
    Switch switchLanguage;
    SharedPreferencesManager sharedPreferencesManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferencesManager = new SharedPreferencesManager(LoginActivity.this);
        if (sharedPreferencesManager.getBoolean(SharedPreferencesManager.IS_ARABIC,LoginActivity.this)){
            changeLang(getApplicationContext(),"ar");
        }
        setContentView(R.layout.activity_login);
        initViews();
        initObj();
        initListeners();

    }

    private void initViews() {
        btnLogin = (Button) findViewById(R.id.btnLogin);
        etUser = (EditText) findViewById(R.id.etUser);
        etPass = (EditText) findViewById(R.id.etPass);
        switchLanguage = (Switch) findViewById(R.id.mySwitch);
        etPass.setText("admin");
        etUser.setText("admin");
    }

    private void initObj() {



        switchLanguage = (Switch) findViewById(R.id.mySwitch);
        if (sharedPreferencesManager.getBoolean(SharedPreferencesManager.IS_ARABIC,LoginActivity.this)){
            switchLanguage.setChecked(true);
        }
    }

    private void initListeners() {
        btnLogin.setOnClickListener(mGlobal_OnClickListener);

        switchLanguage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    changeLang(LoginActivity.this,"ar");
                    sharedPreferencesManager.setBoolean(SharedPreferencesManager.IS_ARABIC,true,getApplicationContext());
                    restartActivity();
                }else{
                    changeLang(LoginActivity.this,"en");
                    sharedPreferencesManager.setBoolean(SharedPreferencesManager.IS_ARABIC,false,getApplicationContext());
                    restartActivity();
                }
            }
        });
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
            showToast(getString(R.string.txt_enter_username));
        } else if ("".equals(etPass.getText().toString().trim())) {
            showToast(getString(R.string.txt_enter_pass));
        } else {
            return true;
        }
        return false;
    }

    void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }


    public  void changeLang(Context context, String lang) {
        Locale myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

    private void restartActivity() {
        Intent intent = new Intent(LoginActivity.this,LoginActivity.class);
        finish();
        startActivity(intent);
    }


    @Override
    public void onSuccess(ResponseDTO responseDTO) {
        switch (responseDTO.getCallBackId()) {

            case SystemConstants.RESPONSE_LOGIN:
                LoginResponseDTO loginResponseDTO = (LoginResponseDTO) responseDTO;
                if (responseDTO != null) {
                    if (0!=loginResponseDTO.getUserID()) {
                        CommonActions.DismissesDialog();
                        MyApplication.getInstance().setUserID(loginResponseDTO.getUserID());
                        startActivity(new Intent(LoginActivity.this, BaseActivity.class));
                        finish();
                    } else {
                        CommonActions.DismissesDialog();
                        new AlertDialog.Builder(LoginActivity.this)
                                .setTitle(R.string.txt_login)
                                .setMessage(R.string.msg_login_failed)
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
