package com.morgan.architecturepractice.inject.component;

import com.morgan.architecturepractice.inject.ActivityScope;
import com.morgan.architecturepractice.inject.module.MainActivityModule;
import com.morgan.architecturepractice.ui.MainActivity;
import com.morgan.architecturepractice.ui.presenter.MainPresenter;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = MainActivityModule.class)
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(MainPresenter mainPresenter);
}
