package com.betclic.archimvp.deezer;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Mathieu Bertin on 14/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */
@RealmClass
public class Track implements RealmModel {

    int id;
    String title;

    public Track() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
