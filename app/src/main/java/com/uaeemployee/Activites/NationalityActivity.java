package com.uaeemployee.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.uaeemployee.Adapters.NationalityAdapter;
import com.uaeemployee.R;
import com.uaeemployee.Utils.SharedPreferencesManager;

public class NationalityActivity extends AppCompatActivity {

    ListView lvNationality;
    private Toolbar mToolbar;
    public static int[] imageIds = {R.drawable.flag_pakistan, R.drawable.flag_india, R.drawable.flag_uae};
    public static String[] listCountryName = {"Pakistan", "India", "UAE"};
    public static String[] listPercentages = {"30%", "20%", "50%"};
    SharedPreferencesManager sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nationality);

        initViews();
        initObj();
        initListeners();

    }


    private void initViews() {
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        lvNationality = (ListView) findViewById(R.id.lvNationality);

    }

    private void initObj() {
        setSupportActionBar(mToolbar);
        sharedpreferences = new SharedPreferencesManager(NationalityActivity.this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Select Nationality");
        mToolbar.setNavigationIcon(R.drawable.back_icon);
        lvNationality.setAdapter(new NationalityAdapter(this, listCountryName, listPercentages, imageIds));
    }

    private void initListeners() {
        lvNationality.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {

                    case 0:
                        sharedpreferences.setString(SharedPreferencesManager.CURRENT_NATIONALITY,"pakistan", NationalityActivity.this);
                        startActivity(new Intent(NationalityActivity.this, EmployeeSearchActivity.class));
                        break;

                    case 1:
                        sharedpreferences.setString(SharedPreferencesManager.CURRENT_NATIONALITY,"india", NationalityActivity.this);
                        startActivity(new Intent(NationalityActivity.this, EmployeeSearchActivity.class));
                        break;

                    case 2:
                        sharedpreferences.setString(SharedPreferencesManager.CURRENT_NATIONALITY,"local", NationalityActivity.this);
                        startActivity(new Intent(NationalityActivity.this, EmployeeSearchActivity.class));
                        break;

                }
            }
        });

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
    }

}