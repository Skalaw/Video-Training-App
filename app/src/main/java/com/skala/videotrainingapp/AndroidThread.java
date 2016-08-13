package com.skala.videotrainingapp;

import com.skala.core.uithread.ExecutionThread;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @author Skala
 */
public class AndroidThread implements ExecutionThread {
    @Override
    public Scheduler uiScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
