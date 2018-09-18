package np.gov.thorimun.www.thorimun4;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar;
    GridLayout mainGrid;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mainGrid = findViewById(R.id.mainGrid);
        setSingleEvent(mainGrid);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_manage); // ramu change this drawable icon
    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (finalI == 0)   //Home Page
                    {
                        Intent intent = new Intent(MainActivity.this, New1Activity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 1)   //Office Introduction
                    {
                        Intent intent = new Intent(MainActivity.this, New2Activity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 2)   //Bajet Karyakrm
                    {
                        Intent intent = new Intent(MainActivity.this, New3Activity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 3)   //Bajet Karyakrm
                    {
                        Intent intent = new Intent(MainActivity.this, New4Activity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 4)   //Sushan
                    {
                        Intent intent = new Intent(MainActivity.this, New5Activity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 5)   //Jana pratinidhi
                    {
                        Intent intent = new Intent(MainActivity.this, New6Activity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 6)   //News
                    {
                        Intent intent = new Intent(MainActivity.this, New7Activity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 7)   //gallary
                    {
                        Intent intent = new Intent(MainActivity.this, New8Activity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 8)   //Rules and regulation
                    {
                        Intent intent = new Intent(MainActivity.this, Rules_RegulationActivity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 9)   //staff
                    {
                        Intent intent = new Intent(MainActivity.this, Staff_Activity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 10)   //
                    {
                        Intent intent = new Intent(MainActivity.this, jsonTest.class);
                        startActivity(intent);
                    }


                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.about_setting){
            Toast toast =Toast.makeText(this, "App is Designed by Shrawan Raut, IT officer of Thori Rural Municipality.\n Visit (https://github.com/Shrawan99/Thori_new) for Collaboration.", Toast.LENGTH_LONG);
            View view =toast.getView();
            view.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
            toast.show();

//            return (true);
        }
        if (id == R.id.action_settings) {
            System.exit(0);
//        return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        item.setChecked(true);
        drawer.closeDrawers();

        Log.d("IO", "onNavigationItemSelectedActivated");

        switch (item.getItemId()) {
            case R.id.nav_home: {
                Intent myintnt = new Intent(this, contactActivity.class);
                startActivity(myintnt);
                drawer.closeDrawers();
                break;
            }
            case R.id.nav_gallery: {
                Intent myintnt = new Intent(MainActivity.this, FaqActivity.class);
                startActivity(myintnt);
                drawer.closeDrawers();
                break;
            }
            case R.id.nav_slideshow: {
                Intent myintnt = new Intent(MainActivity.this, New6Activity.class);
                startActivity(myintnt);
                drawer.closeDrawers();
                break;

            }
            case R.id.nav_manage: {
                Intent myintnt = new Intent(MainActivity.this, jsonTest.class);
                startActivity(myintnt);
                break;
            }
            case R.id.nav_share: {
                Intent myintnt = new Intent(MainActivity.this, Staff_Activity.class);
                startActivity(myintnt);
                break;
            }
            case R.id.nav_send: {
                Intent myintnt = new Intent(MainActivity.this, New8Activity.class);
                startActivity(myintnt);
                break;
            }
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}

