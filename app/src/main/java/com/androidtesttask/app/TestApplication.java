package com.androidtesttask.app;

import android.app.Application;

import com.alexvasilkov.events.Events;

/**
 * Date: 18.03.2016
 * Time: 23:15
 *
 * @author Artem Zalevskiy
 */
public class TestApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Events.setAppContext(this);
        Events.setDebug(false);
    }

}

