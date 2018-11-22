package com.weboniselab.android;

/**
 * Created by rohit.anvekar on 9/5/18.
 */

import com.weboniselab.android.di.component.AppComponent;
import com.weboniselab.android.di.module.AppModule;

import org.robolectric.RuntimeEnvironment;

import it.cosenonjaviste.daggermock.DaggerMockRule;

public class RoboelectricTestMockRule extends DaggerMockRule<AppComponent> {

    public RoboelectricTestMockRule() {
        super(AppComponent.class, new AppModule());

        customizeBuilder(new BuilderCustomizer<AppComponent.Builder>() {
            @Override
            public AppComponent.Builder customize(AppComponent.Builder builder) {
                return builder.application(getApplication());
            }
        });

        set(new ComponentSetter<AppComponent>() {
            @Override
            public void setComponent(AppComponent component) {
                component.inject(getApplication());
            }
        });
    }

    private static App getApplication() {
        return ((App) RuntimeEnvironment.application);
    }
}