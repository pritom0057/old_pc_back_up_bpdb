package com.sarwar.bpdb.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.crashlytics.android.Crashlytics;
import com.sarwar.bpdb.R;
import com.sarwar.bpdb.fragments.BIllFragment;
import com.sarwar.bpdb.fragments.BIllFragment_updated;
import com.sarwar.bpdb.fragments.HomeFragment;
import com.sarwar.bpdb.fragments.InfoFragment;
import com.sarwar.bpdb.fragments.LedgerFragment;
import com.sarwar.bpdb.fragments.ManualFragment;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static int mCurrentActiveMenu;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mCurrentActiveMenu = R.id.nav_home;
        displaySelectedScreen(mCurrentActiveMenu);
    }

    @Override
    public void onBackPressed() {
//        forceCrash();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displaySelectedScreen(item.getItemId());
        return true;
    }
    public void forceCrash() {
        throw new RuntimeException("This is a crash");
    }

    private void displaySelectedScreen(int id) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        if (id == R.id.nav_home) {
            mCurrentActiveMenu = R.id.nav_home;
            fragment = new HomeFragment();
        } else if (id == R.id.nav_billinfo) {
            mCurrentActiveMenu = R.id.nav_billinfo;
            fragment = new BIllFragment_updated();
        } else if (id == R.id.nav_ladger) {
            mCurrentActiveMenu = R.id.nav_ladger;
            fragment = new LedgerFragment();
        } else if (id == R.id.nav_manual) {
            mCurrentActiveMenu = R.id.nav_manual;
            fragment = new ManualFragment();

        } else if (id == R.id.nav_about) {
            mCurrentActiveMenu = R.id.nav_about;
            fragment = new InfoFragment();

        } else if (id == R.id.nav_logout) {
            showDIalogForExit();

        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    public void showDIalogForExit() {
        AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
                MainActivity.this);

        alertDialog2.setTitle(getResources().getString(R.string.exit_app));
        alertDialog2.setMessage(getResources().getString(R.string.exit_app_confirm));
        alertDialog2.setIcon(R.drawable.ic_logout);
        alertDialog2.setPositiveButton(getResources().getString(R.string.yes_string),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        alertDialog2.setNegativeButton(getResources().getString(R.string.no_string),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
//                        displaySelectedScreen(mCurrentActiveMenu);
                        navigationView.setCheckedItem(mCurrentActiveMenu);
                    }
                });
        alertDialog2.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            forceCrash();
                showDIalogForExit();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
