package com.skala.core.uithread;

import rx.Scheduler;

/**
 * @author Skala
 */
public interface ExecutionThread {
    Scheduler uiScheduler();
}
