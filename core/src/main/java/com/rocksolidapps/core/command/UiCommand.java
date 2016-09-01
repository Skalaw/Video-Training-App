package com.rocksolidapps.core.command;

/**
 * @author Skala
 */
public interface UiCommand<Ui> {
    void execute(Ui ui);
}
