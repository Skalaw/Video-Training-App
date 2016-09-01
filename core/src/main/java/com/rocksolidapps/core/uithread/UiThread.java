package com.rocksolidapps.core.uithread;

import rx.Scheduler;

/**
 * @author Skala
 */
public final class UiThread {
    private static ExecutionThread executionThread;

    private UiThread() {
    }

    public static void init(ExecutionThread executionThread) {
        UiThread.executionThread = executionThread;
    }

    public static Scheduler uiScheduler() {
        if (executionThread == null) {
            throw new NotImplementedThreadException();
        }
        return executionThread.uiScheduler();
    }
}
