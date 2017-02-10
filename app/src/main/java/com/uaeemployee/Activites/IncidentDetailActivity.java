package com.uaeemployee.Activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.uaeemployee.R;

public class IncidentDetailActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident_detail);

        initViews();
        initObj();
        initListeners();
    }

    private void initViews() {
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void initObj() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Incident Detail");
        mToolbar.setNavigationIcon(R.drawable.back_icon);
    }

    private void initListeners() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
    }
}
