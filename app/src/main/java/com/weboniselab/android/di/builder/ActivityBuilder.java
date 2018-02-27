package com.weboniselab.android.di.builder;

import com.weboniselab.android.ui.story.home.HomeActivity;
import com.weboniselab.android.ui.story.home.HomeModule;
import com.weboniselab.android.ui.story.login.LoginActivity;
import com.weboniselab.android.ui.story.login.LoginActivityModule;
import com.weboniselab.android.ui.story.splash.SplashActivity;
import com.weboniselab.android.ui.story.splash.SplashActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeActivity bindHomeActivity();
}
