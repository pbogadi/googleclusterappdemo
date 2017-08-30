package com.mike.givemewingzz.mapsclusterdemo.service;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by GiveMeWingzz on 11/29/2015.
 */
public abstract class BaseRequestInterceptor implements Interceptor {

    /**
     * Called for every request.
     *
     * @param chain
     */

    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }
}