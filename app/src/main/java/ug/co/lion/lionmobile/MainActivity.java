package ug.co.lion.lionmobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fm;
    SharedPreferences mPositionSavedPrefs;
    SharedPreferences.Editor posSavedEditor;
    DrawerLayout drawer;
    Intent receivedIntent;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        receivedIntent = getIntent();


        fm = getSupportFragmentManager();

        mPositionSavedPrefs = getSharedPreferences("mPositionSaved",
                Context.MODE_PRIVATE);
        posSavedEditor = mPositionSavedPrefs.edit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        showCurrentFragment();


    }

    private void showCurrentFragment() {

        int id = mPositionSavedPrefs.getInt(
                "last_main_position", 1);

        Fragment fragment = null;

        CharSequence title = null;

        if (receivedIntent.hasExtra("app_start")) {
            //if the app is just being started
            drawer.openDrawer(GravityCompat.START);
            fragment = new HomeFragment();
            title = "Home";
            getSupportActionBar().setTitle(title);
            fm.beginTransaction().replace(R.id.contentMain, fragment).commit();
            posSavedEditor.putInt("last_main_position", R.id.nav_home).apply();



        } else {


            if (id == R.id.nav_home) {
                fragment = new HomeFragment();
                title = "Home";
            } else if (id == R.id.nav_products) {

                fragment = new ProductsFragment();
                title = "Our Products";


            } else if (id == R.id.nav_claims) {

                fragment = new ClaimsFragment();
                title = "Claims Center";


            } else if (id == R.id.nav_share) {

            } else if (id == R.id.nav_send) {

            }

            //      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            //      drawer.closeDrawer(GravityCompat.START);
            if (fragment != null) {
                getSupportActionBar().setTitle(title);
                fm.beginTransaction().replace(R.id.contentMain, fragment).commit();
                posSavedEditor.putInt("last_main_position", id).apply();
            }
        }

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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            DbClass dbClass = new DbClass(this);

            dbClass.open();
            String name = dbClass.returnName();
            dbClass.close();

            Toast.makeText(this, "Pdt Name: " + name, Toast.LENGTH_LONG).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        item.setChecked(true);
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_home) {
            // home frag
            fragment = new HomeFragment();

        } else if (id == R.id.nav_products) {

            fragment = new ProductsFragment();


        } else if (id == R.id.nav_claims) {

            fragment = new ClaimsFragment();


        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        if (fragment != null) {
            fm.beginTransaction().replace(R.id.contentMain, fragment).commit();
        }


        drawer.closeDrawer(GravityCompat.START);
        getSupportActionBar().setTitle(item.getTitle());
        posSavedEditor.putInt("last_main_position", id).apply();
        return true;
    }
}
