package com.skala.videotrainingapp.home;

import android.os.Bundle;

import com.skala.videotrainingapp.base.BaseFragmentActivity;
import com.skala.videotrainingapp.R;
import com.skala.videotrainingapp.discovermovie.DiscoverMovieFragment;

/**
 * @author Skala
 */
public class HomeActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contentFragment, new DiscoverMovieFragment(), DiscoverMovieFragment.FRAGMENT_TAG)
                    .commit();
        }
    }
}
