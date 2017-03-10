package com.example.sonumeewa.newsfeed;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.content.AsyncTaskLoader;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static android.view.View.GONE;

public class NewsFeedActivity extends AppCompatActivity implements LoaderCallbacks<List<NewsFeed>>{



    public static final String LOG_TAG = NewsFeedActivity.class.getName();
    public static final String URL_FOR_NEWS = " https://newsapi.org/v1/articles?";
    public NewsFeedAdapter mAdapter = null;
    private static final int NEWSFEEDS_LOADER_ID = 1;
    private TextView mEmptyStateTextView;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<List<NewsFeed>> onCreateLoader(int i, Bundle bundle) {

        SharedPreferences sharedPrefs=PreferenceManager.getDefaultSharedPreferences(this);
        String source=sharedPrefs.getString(getString(R.string.settings_news_source_key),getString(R.string.settings_news_source_default));

        String sortBy = sharedPrefs.getString(
                getString(R.string.settings_sort_by_key),
                getString(R.string.settings_sort_by_default)
        );


        Uri baseUri = Uri.parse(URL_FOR_NEWS);
        Uri.Builder uriBuilder = baseUri.buildUpon();

       uriBuilder.appendQueryParameter("source", source);
        uriBuilder.appendQueryParameter("sortBy", sortBy );
        uriBuilder.appendQueryParameter("apiKey","717e258525624b0a9b6ae5dccb470ecf");

        Log.i(LOG_TAG,"oncreateloadin working");
        return new NewsFeedLoader(this, uriBuilder.toString());


    }


    @Override
    public void onLoadFinished(Loader<List<NewsFeed>> loader, List<NewsFeed> Newsfeeds) {
        mAdapter.clear();
 if (Newsfeeds != null && !Newsfeeds.isEmpty()) {
     Log.i(LOG_TAG,"loadfinished working");
     View loadingIndicator=findViewById(R.id.sort_by_spinner);
     loadingIndicator.setVisibility(GONE);

            mAdapter.addAll(Newsfeeds);

        }
        else
 {
     mEmptyStateTextView=(TextView)findViewById(R.id.empty_view);
     mEmptyStateTextView.setText("NO NEWS FETCHED");
 }
    }
    @Override
    public void onLoaderReset(Loader<List<NewsFeed>> loader) {
        Log.i(LOG_TAG,"onresetloadin working");
        mAdapter.clear();
    }

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



        ConnectivityManager connMgr=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connMgr.getActiveNetworkInfo();

       if(networkInfo!=null&&networkInfo.isConnected()){
           LoaderManager loaderManager = getLoaderManager();

           loaderManager.initLoader(NEWSFEEDS_LOADER_ID, null, this);

       }
        else{
           View loadingIndicator=findViewById(R.id.sort_by_spinner);
           loadingIndicator.setVisibility(GONE);
           mEmptyStateTextView=(TextView)findViewById(R.id.empty_view);
           mEmptyStateTextView.setText("No Ineternet Service");
       }

    }


}
