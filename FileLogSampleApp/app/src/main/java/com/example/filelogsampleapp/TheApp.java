package com.example.filelogsampleapp;

import android.app.Application;
import android.util.Log;

import com.example.filelogsampleapp.logging.FileLog;

/**
 * Created by volker on 10.02.15.
 */
public class TheApp extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        FileLog.open( "sdcard/sampleApp.log", Log.VERBOSE, 1000000 );
    }

}
