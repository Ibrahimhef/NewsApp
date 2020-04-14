package com.example.myapplication;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class CustomLoader extends AsyncTaskLoader<List<CustomNews>> {
    private static final String API_URL =
            "https://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2014-01-01&api-key=test";


    public CustomLoader(@NonNull Context context) {
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
