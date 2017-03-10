package com.example.sonumeewa.newsfeed;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;

import java.util.ArrayList;

public class NewsFeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);



        ArrayList<NewsFeed> newsList = QueryUtils.extractNews();




        ListView newsListView=(ListView)findViewById(R.id.list);
        NewsFeedAdapter newsAdapter=new NewsFeedAdapter(this,newsList);


        newsListView.setAdapter(newsAdapter);


    }

}
