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
    private String getDescription(){
        return mInfo;
    }
    public String getImageUrl(){
        return mImageUrl;
    }
    public String getUrl(){return mUrl;}
    public String getTime(){ return mTime; }

    public NewsFeed(String Title, String Info, String ImageUrl,String Url, String Time){
        mTitle=Title;
        mInfo=Info;
        mImageUrl=ImageUrl;
        mTime=Time;
        mUrl=Url;
    }

}
