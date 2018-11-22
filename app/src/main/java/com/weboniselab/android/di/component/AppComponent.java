package com.weboniselab.android.di.component;

import android.app.Application;

import com.weboniselab.android.App;
import com.weboniselab.android.di.builder.ActivityBuilder;
import com.weboniselab.android.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        Builder appModule(AppModule appModule);

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(App app);

}
