package com.skala.core.api.net;

/**
 * @author Skala
 */
public interface CallApi<T, E> {
    void onSuccess(T t);

    void onFailed(E e);
}
