package com.uaeemployee.Activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.uaeemployee.Network.ResponseDTOs.EmployeeDTO;
import com.uaeemployee.R;
import com.uaeemployee.Utils.SharedPreferencesManager;
import com.uaeemployee.Utils.TextDrawable;

public class EmployeeProfileActivity extends AppCompatActivity {


    TextView tvNameValue, tvGenderValue, tvNationalityValue, tvOrgValue, tvEmailValue, tvContactValue;
    SharedPreferencesManager sharedPreferencesManager;
    private Toolbar mToolbar;
    ImageView ivLetter;
    TextDrawable drawable;
    EmployeeDTO employeeDTO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initViews();
        initObj();
        initListeners();
        setEmployeeData(employeeDTO);
    }

    private void initViews() {
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        tvNameValue = (TextView) findViewById(R.id.tvName);
        tvGenderValue = (TextView) findViewById(R.id.tvEmployeeID);
        tvNationalityValue = (TextView) findViewById(R.id.tvNationalityValue);
        tvOrgValue = (TextView) findViewById(R.id.tvOrgValue);
        tvEmailValue = (TextView) findViewById(R.id.tvEmailValue);
        tvContactValue = (TextView) findViewById(R.id.tvContactValue);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        ivLetter = (ImageView) findViewById(R.id.ivLetter);
    }

    private void initObj() {
        sharedPreferencesManager = new SharedPreferencesManager(EmployeeProfileActivity.this);
        employeeDTO = (EmployeeDTO) getIntent().getSerializableExtra("employee_DTO_Obj");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Employee Profile");
        mToolbar.setNavigationIcon(R.drawable.back_icon);

        String name = employeeDTO.getFirstName().trim()+" "+employeeDTO.getLastName().trim();
        String[] splitStr = name.split("\\s+");

        if ("".equals(splitStr[1])) {
            drawable = TextDrawable.builder()
                    .beginConfig()
                    .withBorder(6, getResources().getColor(R.color.white))
                    .endConfig()
                    .buildRound(splitStr[0].substring(0, 1).toUpperCase(), getResources().getColor(R.color.colorPrimaryDark));

        } else {
            drawable = TextDrawable.builder()
                    .beginConfig()
                    .withBorder(6, getResources().getColor(R.color.white))
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


    public void setEmployeeData(EmployeeDTO employeeDTO) {
        tvNameValue.setText(employeeDTO.getFirstName()+" "+ employeeDTO.getLastName());
        tvGenderValue.setText(employeeDTO.getGender());
        tvNationalityValue.setText(employeeDTO.getCountryName());
        tvOrgValue.setText("Emmar");
        tvEmailValue.setText(employeeDTO.getEmail());
        tvContactValue.setText(employeeDTO.getContactNo());
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