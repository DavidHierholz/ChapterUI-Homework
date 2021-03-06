package com.example.david.chapterui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private  DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set Toolbar as Actionbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Enable the bars navigation button
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);


        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                item.setChecked(true);
                int id = item.getItemId();

                FragmentManager fragmentManager = getSupportFragmentManager();
                switch (id) {
                    case R.id.nav_bmi:
                        setTitle("Bmi Calculator");
                        BmiCalculatorFragment calculatorFragment = new BmiCalculatorFragment();
                        fragmentManager.beginTransaction().replace(R.id.fragment, calculatorFragment).commit();
                        break;
                    case R.id.nav_reaction:
                        setTitle("Reaction Game");
                        ReactionGameFragment reactionGameFragment = new ReactionGameFragment();
                        fragmentManager.beginTransaction().replace(R.id.fragment, reactionGameFragment).commit();
                        break;
                    case R.id.step_counter:
                        setTitle("Step Counter");
                        StepCounterFragment stepCounterFragment = new StepCounterFragment();
                        fragmentManager.beginTransaction().replace(R.id.fragment, stepCounterFragment).commit();
                        break;
                    default:
                }

                drawer.closeDrawers();
                return true;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
