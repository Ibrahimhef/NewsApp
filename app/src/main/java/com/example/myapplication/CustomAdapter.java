package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter  extends ArrayAdapter<CustomNews> {

    public CustomAdapter(Context context, List<CustomNews> customNews) {
        super(context, 0, customNews);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(
                    R.layout.layou_viwer, parent, false);
        }

        CustomNews currentNews = getItem(position);
        TextView newsTitleTextView = (TextView) view.findViewById(R.id.title_text_view);
        String title = currentNews.getWebTitle();
        newsTitleTextView.setText(title);


        TextView newsCategorytextView = (TextView) view.findViewById(R.id.category_text_view);
        String category = currentNews.getSectionName();
        newsCategorytextView.setText(category);

        TextView newsDatetextView = (TextView) view.findViewById(R.id.date_text_view);
        String date = currentNews.getWebPublicationDate();
        newsDatetextView.setText(date);

        TextView newsAuthortextView = (TextView) view.findViewById(R.id.author_text_view);
        String author = currentNews.getAuthor();
        newsAuthortextView.setText(author);

        return view;
    }
}
