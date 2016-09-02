package com.rocksolidapps.movies.home;

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
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rocksolidapps.movies.BuildConfig;
import com.rocksolidapps.movies.R;
import com.rocksolidapps.movies.base.BaseFragmentActivity;
import com.rocksolidapps.movies.discovermovie.DiscoverMovieFragment;
import com.rocksolidapps.movies.moviedescription.MovieDescriptionFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Skala
 */
public class HomeActivity extends BaseFragmentActivity implements HomeUi {
    private ActionBarDrawerToggle drawerToggle;

    @BindView(R.id.btnGenre)
    protected Button btnGenre;
    @BindView(R.id.textGenre)
    protected TextView textGenre;

    @BindView(R.id.btnSort)
    protected Button btnSort;
    @BindView(R.id.textSort)
    protected TextView textSort;

    @BindView(R.id.bottomSheetCancel)
    protected ImageView bottomSheetCancel;

    @BindView(R.id.drawerLayout)
    protected DrawerLayout drawerLayout;

    @BindView(R.id.leftDrawer)
    protected NavigationView leftDrawer;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.fab)
    protected FloatingActionButton fab;
    private BottomSheetBehavior bottomSheetBehavior;

    @OnClick(R.id.bottomSheetCancel)
    protected void bottomSheetCancelClick() {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initDrawerLayout();
        initBottomSheet();
        initActionBar();
        initBtnSort();

        getSupportFragmentManager().addOnBackStackChangedListener(this::fragmentChanged);
        if (savedInstanceState == null) {
            openDiscoverMovie();
        } else {
            setFabAndBottomSheet();
        }
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        setHomeAsUpEnabled();
    }

    private void initDrawerLayout() {
        leftDrawer.setNavigationItemSelectedListener(menuItem -> {
            int idItem = menuItem.getItemId();
            if (menuItem.getGroupId() == R.id.menuNavigation) {
                menuItem.setChecked(true);
                openFragment(idItem);
            } else if (idItem == R.id.itemSettings) {
                // todo add settings
                Toast.makeText(getApplicationContext(), "As will soon be available", Toast.LENGTH_SHORT).show();
            } else if (idItem == R.id.itemAbout) {
                showDialogAbout();
            }
            drawerLayout.closeDrawers();
            return true;
        });

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        leftDrawer.setCheckedItem(R.id.itemDiscoverMovie); // todo refactor this when i add next fragment on menuNavigation
    }

    private void openFragment(int idItem) {
        if (idItem == R.id.itemDiscoverMovie) {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.contentFragment);
            if (!(fragment instanceof DiscoverMovieFragment)) {
                openDiscoverMovie();
            }
        }
    }

    private void showDialogAbout() {
        String message = getString(R.string.dialog_about_message, BuildConfig.VERSION_NAME);
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle(R.string.app_name)
                .setMessage(message)
                .setPositiveButton(R.string.dialog_about_close, null);
        builder.show();
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

    private void initBtnSort() {
        btnSort.setOnClickListener(v -> {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.contentFragment);
            if (fragment instanceof DiscoverMovieFragment) {
                ((DiscoverMovieFragment) fragment).showSortList();
            }
        });
    }

    private void fragmentChanged() {
        setFabAndBottomSheet();
        setDefaultToolbar();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(leftDrawer)) {
            drawerLayout.closeDrawers();
            return;
        } else if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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
        setHomeAsUpEnabled();
    }

    private void setHomeAsUpEnabled() { // todo ok now really i want to move description movie to another activity
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            return;
        }

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.contentFragment);
        if (fragment instanceof MovieDescriptionFragment) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        } else {
            actionBar.setDisplayHomeAsUpEnabled(true);
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            drawerToggle.syncState();
        }
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
    public void setButtonGenre(String genre) {
        btnGenre.setText(genre);
    }

    @Override
    public void setButtonSort(String sort) {
        btnSort.setText(sort);
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