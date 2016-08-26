package com.morgan.architecturepractice.inject.module;

import com.morgan.architecturepractice.service.IBingService;
import com.morgan.architecturepractice.service.impl.BingServiceImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceModule {


    @Provides
    IBingService bingService() {
        return new BingServiceImpl();
    }

}
