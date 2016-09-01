package com.rocksolidapps.core.uithread;

/**
 * @author Skala
 */
public class NotImplementedThreadException extends RuntimeException {
    public NotImplementedThreadException() {
        super("Please implement thread");
    }
}
