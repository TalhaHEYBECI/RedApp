package com.dgpays.movieapi.cammon;

import com.google.gson.Gson;

public class GsonBuilder{

    private static GsonBuilder sInstance;
    private Gson mGson;

    public static GsonBuilder getInstance() {
        if (sInstance == null) {
            sInstance = new GsonBuilder();
        }
        return sInstance;
    }

    public Gson getGson() {
        if (mGson == null)
            mGson = new Gson();
        return mGson;
    }
}