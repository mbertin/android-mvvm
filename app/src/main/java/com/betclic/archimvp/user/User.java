package com.betclic.archimvp.user;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Mathieu Bertin on 08/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */
public class User extends RealmObject {

    @PrimaryKey
    protected int id;

}
