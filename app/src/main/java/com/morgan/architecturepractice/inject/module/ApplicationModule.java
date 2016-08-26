package com.morgan.architecturepractice.inject.module;

import com.morgan.architecturepractice.AppContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private AppContext appContext;


    public ApplicationModule(AppContext appContext) {
        this.appContext = appContext;
    }

    @Provides
    @Singleton
    public AppContext appContext() {
        return appContext;
    }


}
