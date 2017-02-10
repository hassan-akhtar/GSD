package com.uaeemployee.Activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.uaeemployee.R;
import com.uaeemployee.Utils.SharedPreferencesManager;
import com.uaeemployee.Utils.TextDrawable;

public class EmployeeProfileActivity extends AppCompatActivity {


    TextView tvNameValue, tvGenderValue, tvNationalityValue;
    SharedPreferencesManager sharedPreferencesManager;
    private Toolbar mToolbar;
    ImageView ivLetter;
    TextDrawable drawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initViews();
        initObj();
        initListeners();


        tvNameValue.setText(sharedPreferencesManager.getString(SharedPreferencesManager.CURRENT_FIRST, EmployeeProfileActivity.this));
        tvGenderValue.setText(sharedPreferencesManager.getString(SharedPreferencesManager.CURRENT_SECOND, EmployeeProfileActivity.this));
        tvNationalityValue.setText(sharedPreferencesManager.getString(SharedPreferencesManager.CURRENT_THIRD, EmployeeProfileActivity.this));
    }

    private void initViews() {
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        tvNameValue = (TextView) findViewById(R.id.tvName);
        tvGenderValue = (TextView) findViewById(R.id.tvGenderValue);
        tvNationalityValue = (TextView) findViewById(R.id.tvNationalityValue);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        ivLetter = (ImageView) findViewById(R.id.ivLetter);
    }

    private void initObj() {
        sharedPreferencesManager = new SharedPreferencesManager(EmployeeProfileActivity.this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Employee Profile");
        mToolbar.setNavigationIcon(R.drawable.back_icon);

        String name = sharedPreferencesManager.getString(SharedPreferencesManager.CURRENT_FIRST, EmployeeProfileActivity.this);
        String[] splitStr = name.split("\\s+");

        if ("".equals(splitStr[1])) {
            drawable = TextDrawable.builder()
                    .beginConfig()
                    .withBorder(6,getResources().getColor(R.color.white))
                    .endConfig()
                    .buildRound(splitStr[0].substring(0, 1).toUpperCase(), getResources().getColor(R.color.colorPrimaryDark));

        } else {
            drawable = TextDrawable.builder()
                    .beginConfig()
                    .withBorder(6,getResources().getColor(R.color.white))
                    .endConfig()
                    .buildRound(splitStr[0].substring(0, 1).toUpperCase() + splitStr[1].substring(0, 1).toUpperCase(), getResources().getColor(R.color.colorPrimaryDark));

        }
        ivLetter.setImageDrawable(drawable);
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