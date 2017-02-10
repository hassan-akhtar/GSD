package com.uaeemployee.Activites;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.uaeemployee.Fragments.DashboardFragment;
import com.uaeemployee.Fragments.EmployeeDocumentFragment;
import com.uaeemployee.Fragments.EmployeeSearchFragment;
import com.uaeemployee.Fragments.EventAndSecurityFragment;
import com.uaeemployee.Fragments.FragmentDrawer;
import com.uaeemployee.Fragments.IncidentManagmentFragment;
import com.uaeemployee.Fragments.OrganizationFragment;
import com.uaeemployee.Fragments.SubOrganizationFragment;
import com.uaeemployee.Fragments.VacanciesFragment;
import com.uaeemployee.Interfaces.Communicator;
import com.uaeemployee.R;

public class BaseActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener, Communicator {


    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private static FragmentManager fragmentManager;
    String title;
    public static Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        fragmentManager = getSupportFragmentManager();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
        displayView(0);

    }

    private void displayView(int position) {
        Fragment fragment = null;
        title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new DashboardFragment();
                title = "Main Menu";
                break;
            case 1:
                fragment = new OrganizationFragment();
                title = "Organization";
                break;

            case 2:
                fragment = new VacanciesFragment();
                title = "Vacancies";
                break;
            case 3:
                fragment = new EmployeeSearchFragment();
                title = "Employee Search";
                break;
            case 4:
                fragment = new EmployeeDocumentFragment();
                title = "Employee Document";
                break;

            case 5:
                fragment = new IncidentManagmentFragment();
                title = "Incident Management";
                break;

            case 6:
                fragment = new EventAndSecurityFragment();
                title = "Event & Security Plan";
                break;

            case 7:
                startActivity(new Intent(BaseActivity.this, LoginActivity.class));
                finish();
                Toast.makeText(getApplicationContext(),"Successfully Logged out!",Toast.LENGTH_LONG).show();

                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    public static void refreshMainViewByNew(Fragment fragment) {
        if (fragment instanceof DashboardFragment) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container_body, new DashboardFragment())
                    .commit();
        } else if (fragment instanceof VacanciesFragment) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.container_body,
                            new VacanciesFragment()).commit();
        } else if (fragment instanceof EmployeeSearchFragment) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.container_body,
                            new EmployeeSearchFragment()).commit();
        } else if (fragment instanceof EmployeeDocumentFragment) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.container_body,
                            new EmployeeDocumentFragment()).commit();
        } else if (fragment instanceof OrganizationFragment) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.container_body,
                            new OrganizationFragment()).commit();
        } else if (fragment instanceof IncidentManagmentFragment) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.container_body,
                            new IncidentManagmentFragment()).commit();
        } else if (fragment instanceof EventAndSecurityFragment) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.container_body,
                            new EventAndSecurityFragment()).commit();
        }else if (fragment instanceof SubOrganizationFragment) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.container_body,
                            new SubOrganizationFragment()).commit();
        }
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    @Override
    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onBackPressed() {

        if (!(BaseActivity.fragment instanceof DashboardFragment)) {
            getSupportActionBar().setTitle("Dashboard");
            refreshMainViewByNew(new DashboardFragment());
        } else {
            new AlertDialog.Builder(BaseActivity.this)
                    .setTitle("Logout")
                    .setMessage("Are you sure you want to Logout?")
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent in = new Intent(BaseActivity.this,
                                    LoginActivity.class);
                            startActivity(in);
                            finish();
                        }
                    })
                    .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(getResources().getDrawable(R.drawable.icon_logout))
                    .show();
        }

    }
}