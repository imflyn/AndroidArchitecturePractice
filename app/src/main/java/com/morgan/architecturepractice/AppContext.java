package com.morgan.architecturepractice;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.MemoryCategory;
import com.morgan.architecturepractice.inject.component.ApplicationComponent;
import com.morgan.architecturepractice.inject.component.DaggerApplicationComponent;
import com.morgan.architecturepractice.inject.module.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;


public class AppContext extends Application {

    private static AppContext appContext;
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        LeakCanary.install(this);
        Glide.get(this).setMemoryCategory(MemoryCategory.HIGH);

        initComponent();
    }

    public static AppContext getInstance() {
        return appContext;
    }

    private void initComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

}
