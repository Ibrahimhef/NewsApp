package com.example.myapplication;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class Loader extends AsyncTaskLoader<List<CustomNews>> {
    private static final String API_URL =
            "http://content.guardianapis.com/search?show-tags=contributor&api-key=test";


    public Loader(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<CustomNews> loadInBackground() {
        if (API_URL == null) {
            return null;
        }

        List<CustomNews> customNews = MyQuery.NewsData(API_URL);
        return customNews;
    }

}
