package com.example.sonumeewa.newsfeed;

import java.util.Date;






public class NewsFeed {
    private String mTitle;
    private String mInfo;
    private String mImageUrl;
    private String mUrl;
    private String mTime;


    public String getTitle(){
        return mTitle;
    }
    public String getDescription(){
        return mInfo;
    }
    public String getImageUrl(){
        return mImageUrl;
    }
    public String getUrl(){return mUrl;}
    public String getTime(){ return mTime; }

    public NewsFeed(String Titles, String Info, String ImageUrl,String Url, String Time){
        mTitle=Titles;
        mInfo=Info;
        mImageUrl=ImageUrl;
        mTime=Time;
        mUrl=Url;
    }

}
