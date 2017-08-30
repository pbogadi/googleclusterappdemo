package com.mike.givemewingzz.mapsclusterdemo.utils;

import android.content.Context;

import io.realm.RealmConfiguration;

/**
 * Create helper class for realm config
 */

public class DBHelper {

    public static final long SCHEMA_VERSION = 1;
    public static final String REALM_NAME = "bbva.realm"; // db name

    public static RealmConfiguration getRealmConfig(Context context) {
        return new RealmConfiguration.Builder()
                .name(REALM_NAME)
                .schemaVersion(SCHEMA_VERSION)
                .build();
    }

}
