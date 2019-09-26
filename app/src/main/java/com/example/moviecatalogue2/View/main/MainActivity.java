package com.example.moviecatalogue2.View.main;

import android.app.ActionBar;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviecatalogue2.R;
import com.example.moviecatalogue2.View.favorite.MyFavorite;
import com.example.moviecatalogue2.View.main.fragment.MoviesFragment;
import com.example.moviecatalogue2.View.main.fragment.TVShowsFragment;
import com.example.moviecatalogue2.View.setting.SettingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tab_menu_main) TabLayout tabLayout;
    @BindView(R.id.viewPager_main) ViewPager viewPager;
    @BindView(R.id.no_connection) TextView noConnectionMessage;

    private ViewPagerAdapter viewPagerAdapter;
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.nav_view) NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,  R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.home_menu:
                        Intent intentHomeMenu = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intentHomeMenu);
                        break;
                    case R.id.favorite_menu:
                        Intent intentFavoriteMenu = new Intent(MainActivity.this, MyFavorite.class);
                        startActivity(intentFavoriteMenu);
                        break;
                }

                return true;
            }
        });



        if (isNetworkAvailable()) {
            viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

            // add fragment in here
            viewPagerAdapter.AddFragment(new MoviesFragment(), getString(R.string.menu_movies_item));
            viewPagerAdapter.AddFragment(new TVShowsFragment(), getString(R.string.menu_tv_shows_item));


            viewPager.setAdapter(viewPagerAdapter);
            tabLayout.setupWithViewPager(viewPager);
        } else {
            tabLayout.setVisibility(View.GONE);
            noConnectionMessage.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        if (item.getItemId() == R.id.action_change_settings) {
            Intent intentChangeSetting = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intentChangeSetting);
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}