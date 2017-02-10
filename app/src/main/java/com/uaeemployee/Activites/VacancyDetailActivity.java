package com.uaeemployee.Activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.uaeemployee.R;
import com.uaeemployee.Utils.SharedPreferencesManager;

public class VacancyDetailActivity extends AppCompatActivity {


   // TextView tvNameValue, tvGenderValue,tvNationalityValue;
    SharedPreferencesManager sharedPreferencesManager;
    private Toolbar mToolbar;
    TextView tvJobTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacancy_detail);

        initViews();
        initObj();
        initListeners();


        //tvNameValue.setText(sharedPreferencesManager.getString(SharedPreferencesManager.CURRENT_FIRST,VacancyDetailActivity.this));
       // tvGenderValue.setText(sharedPreferencesManager.getString(SharedPreferencesManager.CURRENT_SECOND,VacancyDetailActivity.this));
       // tvNationalityValue.setText(sharedPreferencesManager.getString(SharedPreferencesManager.CURRENT_THIRD,VacancyDetailActivity.this));
        tvJobTitle.setText(sharedPreferencesManager.getString(SharedPreferencesManager.CURRENT_FIRST,VacancyDetailActivity.this));
    }

    private void initViews() {
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
       // tvNameValue = (TextView) findViewById(R.id.tvNameValue);
      //  tvGenderValue = (TextView) findViewById(R.id.tvGenderValue);
       // tvNationalityValue = (TextView) findViewById(R.id.tvNationalityValue);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        tvJobTitle = (TextView) findViewById(R.id.tvJobTitle);
    }

    private void initObj() {
        sharedPreferencesManager = new SharedPreferencesManager(VacancyDetailActivity.this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Vacancy Detail");
        mToolbar.setNavigationIcon(R.drawable.back_icon);
    }

    private void initListeners() {

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    final View.OnClickListener mGlobal_OnClickListener = new View.OnClickListener() {
        public void onClick(final View v) {
            switch (v.getId()) {
                case R.id.btnLogin: {
                    break;
                }
            }
        }

    };
}