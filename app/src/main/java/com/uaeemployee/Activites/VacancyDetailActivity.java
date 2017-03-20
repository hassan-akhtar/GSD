package com.uaeemployee.Activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.uaeemployee.Adapters.SubOrganizationAdapter;
import com.uaeemployee.Network.ResponseDTOs.SubOrganizationsDTO;
import com.uaeemployee.Network.ResponseDTOs.VacanciesDTO;
import com.uaeemployee.R;
import com.uaeemployee.Utils.CommonActions;
import com.uaeemployee.Utils.SharedPreferencesManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;


public class VacancyDetailActivity extends AppCompatActivity {


    TextView tvJobTitle, tvSalaryValue, tvJobTypeValue, tvLevelValue, tvLocationValue, tvJobDescriptionValue, tvOrgValue;
    SharedPreferencesManager sharedPreferencesManager;
    private Toolbar mToolbar;
    VacanciesDTO vacanciesDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacancy_detail);

        initViews();
        initObj();
        initListeners();
        setVacanciesData( vacanciesDTO);


    }

    private void initViews() {
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        tvJobTitle = (TextView) findViewById(R.id.tvJobTitle);
        tvSalaryValue = (TextView) findViewById(R.id.tvSalaryValue);
        tvJobTypeValue = (TextView) findViewById(R.id.tvJobTypeValue);
        tvLevelValue = (TextView) findViewById(R.id.tvLevelValue);
        tvOrgValue  = (TextView) findViewById(R.id.tvOrgValue);
        tvLocationValue = (TextView) findViewById(R.id.tvLocationValue);
        tvJobDescriptionValue  = (TextView) findViewById(R.id.tvJobDescriptionValue);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

    }

    private void initObj() {
        sharedPreferencesManager = new SharedPreferencesManager(VacancyDetailActivity.this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.txt_vac_detail);
        mToolbar.setNavigationIcon(R.drawable.back_icon);
        vacanciesDTO = (VacanciesDTO) getIntent().getSerializableExtra(getString(R.string.bundle_vac_dto));

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
    //endregion

    public void setVacanciesData(VacanciesDTO vacanciesDTO) {
        tvJobTitle.setText(vacanciesDTO.getTitle());
        tvSalaryValue.setText(R.string.txt_salary_range);
        tvJobTypeValue.setText(vacanciesDTO.getJobType());
        tvLevelValue.setText(vacanciesDTO.getJobLevel());
        tvLocationValue.setText(getString(R.string.txt_UAE));
        tvOrgValue.setText(vacanciesDTO.getOrganization());
        tvJobDescriptionValue.setText(vacanciesDTO.getDescription());
    }
}