package com.skala.videotrainingapp.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;

import com.skala.videotrainingapp.R;
import com.skala.videotrainingapp.base.BaseFragmentActivity;
import com.skala.videotrainingapp.discovermovie.DiscoverMovieFragment;
import com.skala.videotrainingapp.moviedescription.MovieDescriptionFragment;

/**
 * @author Skala
 */
public class HomeActivity extends BaseFragmentActivity implements HomeUi {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportFragmentManager().addOnBackStackChangedListener(this::setDefaultToolbar);

        if (savedInstanceState == null) {
            openDiscoverMovie();
        }
    }

    private void setDefaultToolbar() {
        setToolbarTitle(getString(R.string.app_name));
        updateToolbarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary),
                ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
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
    public void openMovieDescription(int movieId) {
        MovieDescriptionFragment fragment = MovieDescriptionFragment.newInstance(movieId);
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
