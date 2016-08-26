package com.morgan.architecturepractice.service;


import com.morgan.architecturepractice.api.model.BingImage;

public interface IBingService {


    BingImage getBingImageFromHttp() throws Exception;

}
