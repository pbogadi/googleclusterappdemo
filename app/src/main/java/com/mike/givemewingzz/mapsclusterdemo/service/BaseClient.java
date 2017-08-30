package com.mike.givemewingzz.mapsclusterdemo.service;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mike.givemewingzz.mapsclusterdemo.utils.AppConstants;
import com.squareup.okhttp.OkHttpClient;

import io.realm.RealmObject;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

// Create retrofit and OkHttp instances
public class BaseClient {

    public static final String TAG = BaseClient.class.getSimpleName();

    private BaseClient() {
    }

    // Get Lazy Instance of RetrofitInterface
    public static RetrofitInterface getBBSIClient() {
        return LazySIDCInterface.INSTANCE;
    }

    private static class LazySIDCInterface {
        private static final RetrofitInterface INSTANCE = initializeInterface();

        private static RetrofitInterface initializeInterface() {
            // Create the necessary GSON to handle exclusion of Realm pieces
            Gson gson = new GsonBuilder()
                    .setExclusionStrategies(new ExclusionStrategy() {
                        @Override
                        public boolean shouldSkipField(FieldAttributes f) {
                            return f.getDeclaringClass().equals(RealmObject.class);
                        }

                        @Override
                        public boolean shouldSkipClass(Class<?> clazz) {
                            return false;
                        }
                    })
                    .create();

            // Configure OkHttp+AppD
            OkHttpClient client = new OkHttpClient();

            // Create retrofit instance
            Retrofit retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(AppConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            return retrofit.create(RetrofitInterface.class);
        }
    }

}
