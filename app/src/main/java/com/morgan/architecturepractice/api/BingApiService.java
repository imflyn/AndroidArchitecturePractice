package com.morgan.architecturepractice.api;


import com.morgan.architecturepractice.api.model.BingImage;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BingApiService {


    @GET("/HPImageArchive.aspx?format=js&idx=0&n=1")
    Call<BingImage> getBingImage();
}
