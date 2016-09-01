package com.rocksolidapps.movies;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.WindowManager;

import com.rocksolidapps.core.ui.ScreenSize;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Skala
 */
@Module(library = true)
public class AndroidModule {
    private final Context appContext;

    public AndroidModule(Context appContext) {
        this.appContext = appContext;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return appContext;
    }

    @Provides
    @Singleton
    ScreenSize provideScreenSize(Context context) {
        Point point = new Point();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getSize(point);
        Log.d("SizeScreen", "width: " + point.x + " height: " + point.y);
        return new ScreenSize(point.x);
    }
}
