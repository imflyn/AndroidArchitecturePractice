package com.morgan.architecturepractice.inject.module;

import com.morgan.architecturepractice.inject.ActivityScope;
import com.morgan.architecturepractice.ui.MainActivity;
import com.morgan.architecturepractice.ui.presenter.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    private MainActivity activity;

    public MainActivityModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    MainActivity mainActivity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MainPresenter mainPresenter() {
        return new MainPresenter(activity);
    }
}
