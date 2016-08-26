package com.morgan.architecturepractice;

import android.app.Application;


public class AppContext extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Glide.get(this).setMemoryCategory(MemoryCategory.HIGH);
    }
}
