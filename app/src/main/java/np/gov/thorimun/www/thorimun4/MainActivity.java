package np.gov.thorimun.www.thorimun4;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar=null;

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainGrid = (GridLayout)findViewById(R.id.mainGrid);
        setSingleEvent(mainGrid);

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
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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

                    if (finalI == 0)   //open activity one
                    {
                        Intent intent = new Intent(MainActivity.this, New1Activity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 1)   //open activity one
                    {
                        Intent intent = new Intent(MainActivity.this, New2Activity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 2)   //open activity one
                    {
                        Intent intent = new Intent(MainActivity.this, New3Activity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 3)   //open activity one
                    {
                        Intent intent = new Intent(MainActivity.this, New4Activity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 4)   //open activity one
                    {
                        Intent intent = new Intent(MainActivity.this, New5Activity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 5)   //open activity one
                    {
                        Intent intent = new Intent(MainActivity.this, New6Activity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 6)   //open activity one
                    {
                        Intent intent = new Intent(MainActivity.this, New7Activity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 7)   //open activity one
                    {
                        Intent intent = new Intent(MainActivity.this, New8Activity.class);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
       switch (id){

           case R.id.nav_home:
               Intent h= new Intent(MainActivity.this,MainActivity.class);
                startActivity(h);
                break;

           case R.id.nav_gallery:
               Intent i= new Intent(MainActivity.this,HomeActivity.class);
               startActivity(i);
               break;
            // this is done, now let us go and intialise the home page.
            // after this lets start copying the above.
      }

        /*
        if (id == R.id.nav_home) {
            // Handle the Home action


        Intent myintnt = new Intent(MainActivity.this, New1Activity.class);
        startActivity(myintnt);
        return false;
            //code end

        } else if (id == R.id.nav_gallery) {
            Intent myintnt = new Intent(MainActivity.this,HomeActivity.class);
            startActivity(myintnt);
            return false;

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
