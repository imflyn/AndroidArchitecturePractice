package com.morgan.architecturepractice.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.morgan.architecturepractice.R;
import com.morgan.architecturepractice.api.model.BingImage;
import com.morgan.architecturepractice.inject.component.ActivityComponent;
import com.morgan.architecturepractice.inject.module.MainActivityModule;
import com.morgan.architecturepractice.ui.presenter.MainPresenter;
import com.morgan.architecturepractice.ui.viewmodel.MainViewModel;

import javax.inject.Inject;

import butterknife.BindView;


public class MainActivity extends BaseActivity implements MainViewModel {

    @Inject
    MainPresenter mainPresenter;

    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.iv_content)
    ImageView iv_content;

    @Override
    int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainPresenter.onCreate();
        mainPresenter.getBingImage();
    }

    @Override
    void inject(ActivityComponent activityComponent) {
        activityComponent.plus(new MainActivityModule(this)).inject(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.onDestroy();

    }

    @Override
    public void onGetBingImage(BingImage bingImage) {
        String url = bingImage.getImages().get(0).getUrl();
        Glide.with(this).load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(iv_content) {
            @Override
            protected void setResource(Bitmap resource) {
                iv_content.setImageBitmap(resource);
            }
        });
    }
}
