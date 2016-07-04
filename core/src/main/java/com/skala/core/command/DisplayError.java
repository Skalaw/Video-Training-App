package com.skala.core.command;

import com.skala.core.ui.discovermoviefix.DiscoverMovieUi;

/**
 * @author Skala
 */
public class DisplayError implements UiCommand<DiscoverMovieUi> {
    private final String message;

    public DisplayError(String message) {
        this.message = message;
    }

    @Override
    public void execute(DiscoverMovieUi ui) {
        ui.displayError(message);
    }
}
