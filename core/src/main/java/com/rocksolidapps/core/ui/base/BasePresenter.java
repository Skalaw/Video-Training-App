package com.rocksolidapps.core.ui.base;

import com.rocksolidapps.core.command.UiCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Skała
 */
public class BasePresenter<T extends Ui> {
    protected T ui;
    private final List<UiCommand<T>> uiCommands = new ArrayList<>();
    private boolean uiAttachedBefore = false;

    public void onAttached(T ui) {
        this.ui = ui;
        executePendingUiCommands();
        if (!this.uiAttachedBefore) {
            onFirstUiAttachment();
            this.uiAttachedBefore = true;
        }
    }

    protected void onFirstUiAttachment() {
        // ignored
    }

    public void onDetach() {
        ui = null;
    }

    protected void executePendingUiCommands() {
        int size = uiCommands.size();
        for (int i = 0; i < size; i++) {
            uiCommands.get(i).execute(ui);
        }

        uiCommands.clear();
    }

    protected void execute(UiCommand<T> uiCommand) {
        if (ui == null) {
            uiCommands.add(uiCommand);
        } else {
            uiCommand.execute(ui);
        }
    }
}
