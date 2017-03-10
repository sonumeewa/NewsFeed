package com.example.sonumeewa.newsfeed;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class NewsFeedActivity extends AppCompatActivity {


    public static final String LOG_TAG = NewsFeedActivity.class.getName();
    public static final String URL_FOR_NEWS = " https://newsapi.org/v1/articles?source=bbc-news&sortBy=top&apiKey=717e258525624b0a9b6ae5dccb470ecf";
    public NewsFeedAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);


        ListView newsListView = (ListView) findViewById(R.id.list);
        mAdapter = new NewsFeedAdapter(this, new ArrayList<NewsFeed>());


        newsListView.setAdapter(mAdapter);


        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                NewsFeed currentNews = mAdapter.getItem(position);

                Uri NewsFeedUri = Uri.parse(currentNews.getUrl());

                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, NewsFeedUri);

                startActivity(websiteIntent);
            }
        });

        newsTask task = new newsTask();
        task.execute(URL_FOR_NEWS);


    }

    public class newsTask extends AsyncTask<String, Void, List<NewsFeed>> {
        @Override
        protected List<NewsFeed> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<NewsFeed> result = QueryUtils.fetchNewsFeedData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(List<NewsFeed> data) {
            mAdapter.clear();

            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }
    }
}
