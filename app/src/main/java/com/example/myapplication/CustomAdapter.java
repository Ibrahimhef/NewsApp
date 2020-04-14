package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter<T> extends ArrayAdapter {

    public CustomAdapter(@NonNull Context context, List<CustomNews> informations) {
        super(context, 0, informations);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemViews = convertView;
        if (listItemViews == null) {
            listItemViews = LayoutInflater.from(getContext()).inflate(R.layout.layou_viwer, parent, false);
        }

        CustomNews news = (CustomNews) getItem(position);

        TextView title = (TextView) listItemViews.findViewById(R.id.title);
        TextView date = (TextView) listItemViews.findViewById(R.id.date);
        TextView section = (TextView) listItemViews.findViewById(R.id.section);


        title.setText(news.getWebTitle());
        date.setText(news.getWebPublicationDate());
        section.setText(news.getSectionName());

        return listItemViews;
    }
}
