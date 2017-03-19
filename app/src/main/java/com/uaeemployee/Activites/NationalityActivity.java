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
    List<NationDTO> lst = new ArrayList<NationDTO>();
    int organizationID, orgType, genderType;

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
        getSupportActionBar().setTitle(R.string.txt_select_nationality);
        mToolbar.setNavigationIcon(R.drawable.back_icon);

        lstNation = (NationDTOList) this.getIntent().getExtras().getSerializable("List");
        organizationID = getIntent().getIntExtra("org_Id", 0);
        orgType = getIntent().getIntExtra("org_type", 0);
        genderType = getIntent().getIntExtra("gender_type", 0);

        lst = lstNation.getNationDTO();
        lvNationality.setAdapter(new NationalityAdapter(this, lst));
    }

    private void initListeners() {
        lvNationality.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        sharedpreferences.setString(SharedPreferencesManager.CURRENT_NATIONALITY, getString(R.string.txt_pak), NationalityActivity.this);
                        Intent in = new Intent(NationalityActivity.this, EmployeeSearchActivity.class);
                        in.putExtra("org_Id",organizationID);
                        in.putExtra("org_type",orgType);
                        in.putExtra("gender_type",genderType);
                        in.putExtra("nationality",lst.get(position).getName());
                        startActivity(in);



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