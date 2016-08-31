package com.skala.videotrainingapp.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.skala.videotrainingapp.R;
import com.skala.videotrainingapp.base.BaseFragmentActivity;
import com.skala.videotrainingapp.discovermovie.DiscoverMovieFragment;
import com.skala.videotrainingapp.moviedescription.MovieDescriptionFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Skala
 */
public class HomeActivity extends BaseFragmentActivity implements HomeUi {
    private ActionBarDrawerToggle drawerToggle;

    @BindView(R.id.btnGenre)
    protected Button btnGenre;

    @BindView(R.id.btnSort)
    protected Button btnSort;

    @BindView(R.id.fab)
    protected FloatingActionButton fab;
    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initActionBar();
        initDrawerLayout();
        initBottomSheet();

        getSupportFragmentManager().addOnBackStackChangedListener(this::fragmentChanged);
        if (savedInstanceState == null) {
            openDiscoverMovie();
        } else {
            setFabAndBottomSheet();
        }
    }

    private void initActionBar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initDrawerLayout() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    private void initBottomSheet() {
        View bottomSheet = findViewById(R.id.bottomSheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setPeekHeight(0);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    fab.hide();
                } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.contentFragment);
                    if (fragment instanceof DiscoverMovieFragment) {
                        fab.show();
                    }
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                // ignore
            }
        });

        fab.setOnClickListener(v -> {
            if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            } else {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            return;
        }

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int id = item.getItemId();
        if (id == R.id.menuSettings) {
            Toast.makeText(getApplicationContext(), "menuSettings", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menuAbout) {
            Toast.makeText(getApplicationContext(), "menuAbout", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fragmentChanged() {
        setFabAndBottomSheet();
        setDefaultToolbar();
    }

    private void setFabAndBottomSheet() {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.contentFragment);
        if (fragment instanceof DiscoverMovieFragment) {
            fab.show();
        } else {
            fab.hide();
        }
    }

    private void setDefaultToolbar() {
        Context appContext = getApplicationContext();
        setToolbarTitle(getString(R.string.app_name));
        updateToolbarColor(ContextCompat.getColor(appContext, R.color.colorPrimary), ContextCompat.getColor(appContext, R.color.colorPrimaryDark));
    }

    @Override
    public void setToolbarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void updateToolbarColor(int colorPrimary, int colorPrimaryDark) {
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(colorPrimary));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(colorPrimaryDark);
        }
    }

    @Override
    public void genresIsReady() {
        btnGenre.setEnabled(true);
        btnGenre.setOnClickListener(v -> {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.contentFragment);
            if (fragment instanceof DiscoverMovieFragment) {
                ((DiscoverMovieFragment) fragment).showGenreList();
            }
        });
    }

    @Override
    public void openMovieDescription(int movieId) {
        MovieDescriptionFragment fragment = MovieDescriptionFragment.newInstance(movieId); // todo I think better move this to new activity
        openFragmentOnContent(fragment, MovieDescriptionFragment.FRAGMENT_TAG, true);
    }

    @Override
    public void openYoutube(String url) {
        Uri youtubeUri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, youtubeUri);
        startActivity(intent);
    }

    public void openDiscoverMovie() {
        openFragmentOnContent(new DiscoverMovieFragment(), DiscoverMovieFragment.FRAGMENT_TAG, false);
    }

    @SuppressLint("CommitTransaction")
    public void openFragmentOnContent(Fragment fragment, String fragmentTag, boolean addBackStack) {
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contentFragment, fragment, fragmentTag);
        if (addBackStack) {
            fragmentTransaction.addToBackStack(fragmentTag);
        }
        fragmentTransaction.commit();
    }
}
