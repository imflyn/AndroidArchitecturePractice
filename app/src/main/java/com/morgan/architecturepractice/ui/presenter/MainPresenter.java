package com.morgan.architecturepractice.ui.presenter;


import com.morgan.architecturepractice.api.model.BingImage;
import com.morgan.architecturepractice.inject.component.ActivityComponent;
import com.morgan.architecturepractice.inject.module.MainActivityModule;
import com.morgan.architecturepractice.service.IBingService;
import com.morgan.architecturepractice.ui.MainActivity;
import com.morgan.architecturepractice.ui.viewmodel.MainViewModel;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class MainPresenter extends IPresenter<MainViewModel> {

    @Inject
    IBingService bingService;
    private Subscription getBingImageSubscription;

    public MainPresenter(MainViewModel iView) {
        super(iView);
    }

    @Override
    void inject(ActivityComponent activityComponent) {
        activityComponent.plus(new MainActivityModule((MainActivity) mContext)).inject(this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        if (null != getBingImageSubscription) {
            getBingImageSubscription.unsubscribe();
        }
    }

    public void getBingImage() {
        getBingImageSubscription = Observable.fromCallable(new Callable<BingImage>() {
            @Override
            public BingImage call() throws Exception {
                BingImage bingImage = bingService.getBingImageFromHttp();
                return bingImage;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BingImage>() {
                    @Override
                    public void call(BingImage bingImage) {
                        iView.onGetBingImage(bingImage);
                    }
                });
    }
}
