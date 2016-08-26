package com.morgan.architecturepractice.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.morgan.architecturepractice.AppContext;
import com.morgan.architecturepractice.inject.ActivityScope;
import com.morgan.architecturepractice.inject.component.ActivityComponent;
import com.morgan.architecturepractice.inject.component.DaggerActivityComponent;
import com.morgan.architecturepractice.inject.module.ActivityModule;

import butterknife.ButterKnife;

@ActivityScope
public abstract class BaseActivity extends AppCompatActivity {

    Activity mContext;

    abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initInject();
    }

    private void initInject() {
        ActivityComponent activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(AppContext.getInstance().getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
        inject(activityComponent);
    }

    abstract void inject(ActivityComponent activityComponent);
}
