package com.example.myapplication;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Loader extends AsyncTaskLoader<List<CustomNews>> {


    public Loader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public List<CustomNews> loadInBackground() {
        List<CustomNews> customNews = new ArrayList<>();
        try {
            URL url = new URL("https://content.guardianapis.com/search?api-key=e8daacdb-3f6b-423a-9a66-3b3300cdcb14");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject currentNews = jsonArray.getJSONObject(i);

                customNews.add(new CustomNews(currentNews.getString("webTitle"), currentNews.getString("webPublicationDate"), currentNews.getString("webUrl"), currentNews.getString("webTitle"), currentNews.getString("sectionName")));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
