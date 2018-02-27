
package com.weboniselab.android.ui.story.home.place;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class PlaceFragmentProvider {

    @ContributesAndroidInjector(modules = PlaceFragmentModule.class)
    abstract PlaceFragment provideBlogFragmentFactory();

}
