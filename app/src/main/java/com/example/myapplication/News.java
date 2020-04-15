package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class News extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, LoaderManager.LoaderCallbacks<List<CustomNews>> {
    @BindView(R.id.main_drew)
    DrawerLayout drawerLayout;
    @BindView(R.id.main_linear)
    LinearLayout linearLayout;
    @BindView(R.id.frame)
    FrameLayout frameLayout;
    @BindView(R.id.mian_nav)
    NavigationView navigationView;
    @BindView(R.id.nocontent)
    TextView noConect;
    ListView listView;
    static List<CustomNews> customNewsList;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
         listView = findViewById(R.id.list_item_);
        listView.setEmptyView(noConect);
        customAdapter = new CustomAdapter(this,new ArrayList<CustomNews>());

        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomNews currentNews = customAdapter.getItem(position);
                Uri newsUri = Uri.parse(currentNews.getWebUrl());

                Intent websiteIntent = new Intent(Intent.ACTION_VIEW);
                websiteIntent.setData(newsUri);
                startActivity(websiteIntent);
            }
        });
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()){
            LoaderManager.getInstance(this).initLoader(1,null,this);
        }else {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            noConect.setText(R.string.noConect);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_all:
                List<CustomNews> all = customNewsList;
                customAdapter = new CustomAdapter(this,all);
                listView.setAdapter(customAdapter);
                break;
            case R.id.nav_fainance:

                break;
            case R.id.nav_fashion:

                break;
            case R.id.nav_seinece:

                break;
            case R.id.nav_sport:

                break;
            case R.id.nav_support:
                startActivity(new Intent(News.this, Support.class));
                break;
            case R.id.nav_world:

                break;
            case R.id.nav_tech:

                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }


    @NonNull
    @Override
    public Loader<List<CustomNews>> onCreateLoader(int id, @Nullable Bundle args) {
        return new CustomLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<CustomNews>> loader, List<CustomNews> data) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        noConect.setText(R.string.noNwes);
        customAdapter.clear();
        customNewsList=data;
        if (data != null && !data.isEmpty()) {
            customAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<CustomNews>> loader) {
        customAdapter.clear();
    }
}
