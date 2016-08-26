package com.morgan.architecturepractice.ui.presenter;


import android.app.Activity;
import android.support.v4.app.Fragment;

import com.morgan.architecturepractice.AppContext;
import com.morgan.architecturepractice.inject.component.ActivityComponent;
import com.morgan.architecturepractice.inject.component.DaggerActivityComponent;
import com.morgan.architecturepractice.inject.module.ActivityModule;
import com.morgan.architecturepractice.ui.viewmodel.IView;

public abstract class IPresenter<T extends IView> {

    protected Activity mContext;
    protected T iView;

    public IPresenter(T iView) {
        if (iView instanceof Activity) {
            mContext = (Activity) iView;
        } else if (iView instanceof Fragment) {
            mContext = ((Fragment) iView).getActivity();
        } else if (iView instanceof android.app.Fragment) {
            mContext = ((android.app.Fragment) iView).getActivity();
        } else {
            throw new IllegalArgumentException("Can not get the activity value");
        }
        this.iView = iView;
        initInject();
    }

    private void initInject() {
        ActivityComponent activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(AppContext.getInstance().getApplicationComponent())
                .activityModule(new ActivityModule(mContext))
                .build();
        inject(activityComponent);
    }

    abstract void inject(ActivityComponent activityComponent);

    public void onCreate() {

    }

    public void onDestroy() {
    }
}
