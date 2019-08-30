package com.example.moviecatalogue2.View.favorite;

import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.moviecatalogue2.R;
import com.example.moviecatalogue2.View.favorite.fragment.FavoriteMovieFragment;
import com.example.moviecatalogue2.View.favorite.fragment.FavoriteTvFragment;
import com.example.moviecatalogue2.View.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFavorite extends AppCompatActivity {

    @BindView(R.id.tab_favorite_menu_main)
    TabLayout tabLayout;
    @BindView(R.id.favorite_viewPager_main)
    ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorite);

        setTitle("My Favorite");

        ButterKnife.bind(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,  R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.home_menu:
                        Intent intentHomeMenu = new Intent(MyFavorite.this, MainActivity.class);
                        startActivity(intentHomeMenu);
                        break;
                    case R.id.favorite_menu:
                        Intent intentFavoriteMenu = new Intent(MyFavorite.this, MyFavorite.class);
                        startActivity(intentFavoriteMenu);
                        break;
                }

                return true;
            }
        });

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        // add fragment in here
        viewPagerAdapter.AddFragment(new FavoriteMovieFragment(), getString(R.string.menu_movies_item_favorite));
        viewPagerAdapter.AddFragment(new FavoriteTvFragment(), getString(R.string.menu_tv_shows_item_favorite));


        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
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
}
