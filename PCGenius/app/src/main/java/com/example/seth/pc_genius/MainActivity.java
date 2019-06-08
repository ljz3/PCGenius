package com.example.seth.pc_genius;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Switch;

import com.example.seth.pc_genius.BuildScreen.BuildsFragment;
import com.example.seth.pc_genius.HomeScreen.HomeFragment;
import com.example.seth.pc_genius.SavedPartsScreen.PartsFragment;
import com.example.seth.pc_genius.Api.SearchFragment;

public class MainActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {
    public static String Sortby="";
    public static boolean darkTheme=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
       Fragment fragment = new HomeFragment();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void displaySelectedScreen(int itemId) {
        Fragment fragment = null;

        switch (itemId) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;
            case R.id.nav_builds:
                fragment = new BuildsFragment();
                break;
            case R.id.nav_parts:
                fragment = new PartsFragment();
                break;
            case R.id.nav_search:
                fragment = new SearchFragment();
                break;
            case R.id.nav_settings:
                fragment = new SettingsFragment();
                break;
            case R.id.nav_help:
                fragment = new HelpAndFeedbackFragment();
                break;
            case R.id.nav_tos:
                fragment = new TermsOfServiceFragment();
                break;

        }
        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.screen_area, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        //calling the method displayselectedscreen and passing the id of selected menu
        displaySelectedScreen(item.getItemId());
        //make this method blank
        return true;
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.Best_match:
                if (checked)
                    Log.i("radioInfo", "Bestmatch checked");
                Sortby = "";

                break;
            case R.id.Alphabetically:
                if (checked)
                    Log.i("radioInfo", "Alpha checked");
                Sortby = "&sort=name.asc";
                break;
            case R.id.Price_low_high:
                if (checked)
                    Log.i("radioInfo", "Pricelow checked");
                Sortby = "&sort=salePrice.asc";
                break;
            case R.id.Price_high_low:
                if (checked)
                    Log.i("radioInfo", "Pricehigh checked");
                Sortby = "&sort=salePrice.dsc";
                break;
        }
    }

        public void onSwitchTheme(View view) {
            // Is the button now checked?
            boolean themeChange = ((Switch) view).isChecked();

            // Check which radio button was clicked
           if (themeChange){
               Log.i("change theme","dark theme");


           }
           else{
               Log.i("change theme","light theme");

           }
            }



}
