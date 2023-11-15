package com.example.secondtrainep;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;




public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    public FrameLayout contentFrame;
    public TextView navHeaderName , navHeaderEmail ;
    public ImageView toolprofile ;
//    private EditText btnShare;

    Toolbar toolbar;
    public static final String SHARED_PREF_NAME = "YourSharedPrefName";
    public static final String LOGGEDIN_SHARED_PREF = "LoggedIn";
    public static final String EMAIL_SHARED_PREF = "Email";

    //thats are not visible all over things of





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // t&conCode start
        // t&condCode  End

        toolprofile = findViewById(R.id.tool_profile);
        toolprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opentoolProfile();
            }
        });
        drawerLayout = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        contentFrame = findViewById(R.id.content_frame);
        toolbar = findViewById(R.id.toolbar);
//        btnShare = findViewById(R.id.btnShare);
        setSupportActionBar(toolbar);



        View headerView = navigationView.getHeaderView(0);
        navHeaderName = headerView.findViewById(R.id.nav_name);
        navHeaderEmail = headerView.findViewById(R.id.nav_email);

        loadUserData();

        getActionBar();

        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            // Handle bottom navigation item clicks here
            switch (item.getItemId()) {
                case R.id.bottom_nav_home:
                    loadFragment(new HomeFragment());
                    return true;
                case R.id.bottom_nav_search:
                    loadFragment(new SearchFragment());
                    return true;

                case R.id.bottom_nav_about:
                    loadFragment(new AboutFragment());
                    return true;

                case R.id.bottom_nav_notifications:
                    loadFragment(new NotificationsFragment());
                    return true;
                case R.id.bottom_nav_profile:
                    loadFragment(new ProfileFragment());
                    return true;

            }
            return false;
        });


        // Set the initial fragment to be loaded
        loadFragment(new HomeFragment());

    }

    private void loadUserData() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "Guest");
        String email = sharedPreferences.getString("email", "guest@example.com");

        navHeaderName.setText(name);
        navHeaderEmail.setText(email);



    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {

            return true;
        }
        return super.onOptionsItemSelected(item);

    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
         switch (item.getItemId()) {
            case R.id.nav_home:
                loadFragment(new HomeFragment());
                break;
            case R.id.nav_profile:
                loadFragment(new ProfileFragment());
                break;
            case R.id.nav_settings:
                loadFragment(new SettingsFragment());
                break;
            case R.id.nav_support:
                loadFragment(new SupportFragment());
                break;
            case R.id.nav_contact:
                loadFragment(new ContactFragment());
                break;
            case R.id.nav_share:
                shareApp();
                break;
            case R.id.nav_about:
                loadFragment(new  AboutFragment());
                break;

            case R.id.nav_TearmsAndCondition:
                loadFragment(new  TearmAndCondFragments());

            case R.id.nav_logout:
                showLogoutConfirmationDialog();
                break;

        }


        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void shareApp() {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Check out this awesome app!";
        String shareSubject = "Download My App";
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share using"));
    }

    public void showLogoutConfirmationDialog() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                         SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                         SharedPreferences.Editor editor = sharedPreferences.edit();

                         editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);
                         editor.putString(Config.EMAIL_SHARED_PREF, "");
                         editor.commit();

                        SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(MainActivity.this);
                        sharedPreferencesManager.setLoggedIn(false);

                        Intent intent = new Intent(MainActivity.this, LogActivity.class);
                        startActivity(intent);
                        finishAffinity();
                    }


                });



        
        alertDialogBuilder.setNegativeButton("No",

                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
//                        finishAffinity();
                    }

                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();



    }

    private void opentoolProfile() {
        Intent intent = new Intent(MainActivity.this, ProfileFragment.class);
        startActivity(intent);
//        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }



    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }





}
