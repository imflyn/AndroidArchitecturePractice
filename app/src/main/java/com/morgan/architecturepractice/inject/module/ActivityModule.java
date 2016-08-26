package com.morgan.architecturepractice.inject.module;


import android.app.Activity;

import com.morgan.architecturepractice.inject.ActivityScope;

import dagger.Module;
import dagger.Provides;

@ActivityScope
@Module
public class ActivityModule {

    protected Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    public Activity activity() {
        return activity;
    }


}
