package com.morgan.architecturepractice.inject.component;

import android.app.Activity;

import com.morgan.architecturepractice.inject.ActivityScope;
import com.morgan.architecturepractice.inject.module.ActivityModule;
import com.morgan.architecturepractice.inject.module.MainActivityModule;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {

    Activity getActivity();

    MainActivityComponent plus(MainActivityModule module);
}
