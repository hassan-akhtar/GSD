package com.uaeemployee.Activites;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.uaeemployee.R;
import com.uaeemployee.Utils.SharedPreferencesManager;

public class GenderActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    RelativeLayout llMale, llFemale, llLocal;
    SharedPreferencesManager sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);

        initViews();
        initObj();
        initListeners();

    }


    private void initViews() {

        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        llMale = (RelativeLayout) findViewById(R.id.llMale);
        llFemale = (RelativeLayout) findViewById(R.id.llFemale);
        llLocal = (RelativeLayout) findViewById(R.id.llLocal);
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
        getSupportActionBar().setTitle("Select Gender");
        sharedpreferences = new SharedPreferencesManager(GenderActivity.this);
    }

    private void initListeners() {
        llMale.setOnClickListener(mGlobal_OnClickListener);
        llFemale.setOnClickListener(mGlobal_OnClickListener);
        llLocal.setOnClickListener(mGlobal_OnClickListener);
    }

    final View.OnClickListener mGlobal_OnClickListener = new View.OnClickListener() {
        public void onClick(final View v) {
            switch (v.getId()) {
                case R.id.llMale: {
                    startActivity(new Intent(GenderActivity.this, NationalityActivity.class));
                    sharedpreferences.setString(SharedPreferencesManager.CURRENT_GENDER,"male", GenderActivity.this);
                    break;
                }

                case R.id.llFemale: {
                    startActivity(new Intent(GenderActivity.this, NationalityActivity.class));
                    sharedpreferences.setString(SharedPreferencesManager.CURRENT_GENDER,"female", GenderActivity.this);
                    break;
                }

                case R.id.llLocal: {
                    startActivity(new Intent(GenderActivity.this, EmployeeSearchActivity.class));
                    sharedpreferences.setString(SharedPreferencesManager.CURRENT_NATIONALITY,"localonly", GenderActivity.this);
                    break;
                }

            }
        }

    };

}
