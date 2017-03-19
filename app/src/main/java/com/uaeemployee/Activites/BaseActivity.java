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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.uaeemployee.Fragments.DashboardFragment;
import com.uaeemployee.Fragments.EmployeeDocumentFragment;
import com.uaeemployee.Fragments.EmployeeSearchFragment;
import com.uaeemployee.Fragments.EventAndSecurityFragment;
import com.uaeemployee.Fragments.FragmentDrawer;
import com.uaeemployee.Fragments.IncidentManagmentFragment;
import com.uaeemployee.Fragments.OrganizationFragment;
import com.uaeemployee.Fragments.SubSubOrganizationFragment;
import com.uaeemployee.Fragments.VacanciesFragment;
import com.uaeemployee.Interfaces.Communicator;
import com.uaeemployee.Models.Organization;
import com.uaeemployee.R;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener, Communicator {


    public Toolbar mToolbar;
    public static boolean isEmployeeDoc = false;
    private FragmentDrawer drawerFragment;
    private static FragmentManager fragmentManager;
    String title;
    public static Fragment fragment;
    public static List<String> companisList = new ArrayList<String>();
    MenuItem item;


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
                title = getString(R.string.txt_main_menu);
                break;
            case 1:
                fragment = new OrganizationFragment();
                title = getString(R.string.txt_Org);
                break;

            case 2:
                fragment = new VacanciesFragment();
                title = getString(R.string.txt_vacancies);
                break;
            case 3:
                fragment = new EmployeeSearchFragment();
                isEmployeeDoc = false;
                title = getString(R.string.txt_employee_search);
                break;
            case 4:
                fragment = new EmployeeSearchFragment();
                isEmployeeDoc = true;
                title = getString(R.string.txt_employee_doc);
                break;

//            case 5:
//                fragment = new IncidentManagmentFragment();
//                title = "Incident Management";
//                break;
//
//            case 6:
//                fragment = new EventAndSecurityFragment();
//                title = "Event & Security Plan";
//                break;

            case 5:
                startActivity(new Intent(BaseActivity.this, LoginActivity.class));
                finish();
                Toast.makeText(getApplicationContext(), R.string.txt_logout_success, Toast.LENGTH_LONG).show();

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
        } else if (fragment instanceof SubSubOrganizationFragment) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.container_body,
                            new SubSubOrganizationFragment()).commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.org_menu, menu);
        item = menu.findItem(R.id.action_orgs);
        if (BaseActivity.fragment instanceof DashboardFragment || BaseActivity.fragment instanceof OrganizationFragment || BaseActivity.fragment instanceof SubSubOrganizationFragment) {
            item.setVisible(false);
            invalidateOptionsMenu();
        } else {
            item.setVisible(true);
            invalidateOptionsMenu();
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_orgs) {
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(BaseActivity.this);
            builderSingle.setIcon(R.drawable.icon_orgs);
            builderSingle.setTitle(R.string.menu_txt_title);
            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(BaseActivity.this, android.R.layout.select_dialog_singlechoice);
            arrayAdapter.add(getString(R.string.txt_emmar));
            arrayAdapter.addAll(companisList);

            builderSingle.setNegativeButton(R.string.txt_cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String strName = arrayAdapter.getItem(which);
                    Toast.makeText(getApplicationContext(), getString(R.string.txt_selected) + strName, Toast.LENGTH_LONG).show();
                }
            });
            builderSingle.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
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

        if (BaseActivity.fragment instanceof SubSubOrganizationFragment) {
            getSupportActionBar().setTitle(getString(R.string.txt_Org));
            refreshMainViewByNew(new OrganizationFragment());
        } else if (!(BaseActivity.fragment instanceof DashboardFragment)) {
            getSupportActionBar().setTitle(getString(R.string.txt_main_menu));
            refreshMainViewByNew(new DashboardFragment());
        } else {
            new AlertDialog.Builder(BaseActivity.this)
                    .setTitle(R.string.txt_logout)
                    .setMessage(R.string.txt_logout_msg)
                    .setPositiveButton(R.string.txt_yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent in = new Intent(BaseActivity.this,
                                    LoginActivity.class);
                            startActivity(in);
                            finish();
                        }
                    })
                    .setNegativeButton(R.string.txt_cancle_large, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(getResources().getDrawable(R.drawable.icon_logout))
                    .show();
        }

    }
}