package com.morgan.architecturepractice.inject.component;

import com.morgan.architecturepractice.AppContext;
import com.morgan.architecturepractice.inject.module.ApplicationModule;
import com.morgan.architecturepractice.inject.module.ServiceModule;
import com.morgan.architecturepractice.service.IBingService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ServiceModule.class})
public interface ApplicationComponent {

    AppContext getContext();

    IBingService getBingService();

}
