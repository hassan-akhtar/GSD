package com.uaeemployee.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.uaeemployee.R;

public class LoginActivity extends AppCompatActivity {

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
                    startActivity(new Intent(LoginActivity.this, BaseActivity.class));
                    finish();
                    break;
                }
            }
        }

    };
}
