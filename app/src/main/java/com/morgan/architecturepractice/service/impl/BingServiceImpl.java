package com.morgan.architecturepractice.service.impl;

import com.morgan.architecturepractice.api.HttpClient;
import com.morgan.architecturepractice.api.model.BingImage;
import com.morgan.architecturepractice.service.IBingService;

import retrofit2.Call;
import retrofit2.Response;


public class BingServiceImpl implements IBingService {


    @Override
    public BingImage getBingImageFromHttp() throws Exception {
        Call<BingImage> bingImageCall = HttpClient.getInstance().bingApiService.getBingImage();
        Response<BingImage> response = bingImageCall.execute();
        BingImage bingImage = response.body();

        return bingImage;
    }
}
