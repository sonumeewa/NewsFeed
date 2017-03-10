package com.example.sonumeewa.newsfeed;

import java.util.Date;






public class NewsFeed {
    private String mTitle;
    private String mInfo;
    private String mImageUrl;
    private String mUrl;
    private String mTime;


    private String getTitle(){
        return mTitle;
    }
    private String getDescription(){
        return mInfo;
    }
    private String getImageUrl(){
        return mImageUrl;
    }
    private String getUrl(){return mUrl;}
    private String getTime(){ return mTime; }

    private NewsFeed(String Title, String Info, String ImageUrl,String Url, String Time){
        mTitle=Title;
        mInfo=Info;
        mImageUrl=ImageUrl;
        mTime=Time;
        mUrl=Url;
    }

}
