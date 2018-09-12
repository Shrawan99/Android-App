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
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar;
    GridLayout mainGrid;
    ActionBarDrawerToggle toggle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //setNavigationViewListener();
        mainGrid = (GridLayout)findViewById(R.id.mainGrid);
        setSingleEvent(mainGrid);
      //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
         toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

   // public void setNavigationViewListener(){

    //}

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (finalI == 0)   //open activity one
                    {
                        Intent intent = new Intent(MainActivity.this, New1Activity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 1)   //Home Page
                    {
                        Intent intent = new Intent(MainActivity.this, New2Activity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 2)   //Office Introduction
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


    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
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
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
        }
        if (id == R.id.about_setting){
            Toast toast =Toast.makeText(this, "App is Designed by Shrawan Raut, IT officer of Thori Rural Municipality.\n Visit (https://github.com/Shrawan99/Thori_new) for Collaboration.", Toast.LENGTH_LONG);
            View view =toast.getView();
            view.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
            toast.show();
            return (true);
        }
        if (id == R.id.action_settings) {
        System.exit(0);
        return true;}

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        item.setChecked(true);
        // close drawer when item is tapped
        drawer.closeDrawers();
        switch (item.getItemId()) {

            case R.id.nav_home: {
                // Handle the Home action
                Intent myintnt = new Intent(MainActivity.this, contactActivity.class);
                startActivity(myintnt);
                drawer.closeDrawers();
                break;
                //code end
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

