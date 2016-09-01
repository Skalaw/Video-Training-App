package com.rocksolidapps.core.uithread;

import rx.Scheduler;

/**
 * @author Skala
 */
public interface ExecutionThread {
    Scheduler uiScheduler();
}
