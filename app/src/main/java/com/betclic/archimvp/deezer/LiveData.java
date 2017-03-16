package com.betclic.archimvp.deezer;

/**
 * Created by Mathieu Bertin on 15/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */

public class LiveData<T> {

    T value;
    boolean isValid;

    public LiveData(T data, boolean isValid) {
        this.value = data;
        this.isValid = isValid;
    }
}
