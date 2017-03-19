package com.uaeemployee.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.uaeemployee.Adapters.NationalityAdapter;
import com.uaeemployee.Network.ResponseDTOs.NationDTO;
import com.uaeemployee.Network.ResponseDTOs.NationDTOList;
import com.uaeemployee.R;
import com.uaeemployee.Utils.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.List;

public class NationalityActivity extends AppCompatActivity {

    ListView lvNationality;
    private Toolbar mToolbar;
    public static int[] imageIds = {R.drawable.flag_pakistan, R.drawable.flag_india, R.drawable.flag_uae};
    public static String[] listCountryName;
    public static String[] listPercentages;
    SharedPreferencesManager sharedpreferences;
    NationDTOList lstNation;
    List<NationDTO>  lst = new ArrayList<NationDTO>();

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
        listPercentages = new String[]{getString(R.string.txt_thirty), getString(R.string.txt_twenty), getString(R.string.txt_fifty)};
        listCountryName = new String[]{getString(R.string.txt_pakistan), getString(R.string.txt_ind), getString(R.string.txt_UAE)};
        sharedpreferences = new SharedPreferencesManager(NationalityActivity.this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.txt_select_nationality);
        mToolbar.setNavigationIcon(R.drawable.back_icon);
        lstNation = (NationDTOList) this.getIntent().getExtras().getSerializable("List");
        lst= lstNation.getNationDTO();
        lvNationality.setAdapter(new NationalityAdapter(this, lst));
    }

    private void initListeners() {
        lvNationality.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {

                    case 0:
                        sharedpreferences.setString(SharedPreferencesManager.CURRENT_NATIONALITY,getString(R.string.txt_pak), NationalityActivity.this);
                        startActivity(new Intent(NationalityActivity.this, EmployeeSearchActivity.class));
                        break;

                    case 1:
                        sharedpreferences.setString(SharedPreferencesManager.CURRENT_NATIONALITY,getString(R.string.txt_india), NationalityActivity.this);
                        startActivity(new Intent(NationalityActivity.this, EmployeeSearchActivity.class));
                        break;

                    case 2:
                        sharedpreferences.setString(SharedPreferencesManager.CURRENT_NATIONALITY,getString(R.string.txt_local), NationalityActivity.this);
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