package com.skala.core.ui;

import java.util.List;

import rx.Observable;

/**
 * @author Skała
 */
public class ScreenSize {
    private final static String PREFIX_WIDTH = "w";

    private final int width;
    private final int height;

    public ScreenSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public String getBackdropSize(List<String> sizes) {
        return Observable.from(sizes)
                .filter(size -> size.startsWith(PREFIX_WIDTH))
                .takeUntil(size -> getNumber(size) > width)
                //.filter(size -> getNumber(size) <= width)
                .lastOrDefault(sizes.get(0))
                .toBlocking().first();
    }

    public String getPosterSize(List<String> sizes) {
        return Observable.from(sizes)
                .filter(size -> size.startsWith(PREFIX_WIDTH))
                .takeUntil(size -> getNumber(size) > width)
                //.filter(size -> getNumber(size) <= width)
                .lastOrDefault(sizes.get(0))
                .toBlocking().first();
    }

    private int getNumber(String size) {
        return Integer.parseInt(size.substring(1));
    }
}
