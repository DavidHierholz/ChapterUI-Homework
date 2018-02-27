package com.example.david.chapterui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private  DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TEST","Testing github");

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
                    default:
                }

                drawer.closeDrawers();
                return true;
            }
        });
    }
}
