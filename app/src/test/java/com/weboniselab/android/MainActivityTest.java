package com.weboniselab.android;

/**
 * Created by rohit.anvekar on 9/5/18.
 */

import com.weboniselab.android.data.AppDataManager;
import com.weboniselab.android.data.remote.ApiService;
import com.weboniselab.android.di.component.AppComponent;
import com.weboniselab.android.ui.story.login.LoginActivity;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import it.cosenonjaviste.daggermock.DaggerMockRule;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityTest {

    @Rule public final DaggerMockRule<AppComponent> rule = new RoboelectricTestMockRule();

    @Mock
    AppDataManager restService;

    @Test
    public void testOnCreate() {
        //when(restService.getPlace()).thenReturn(true);

        Assert.assertNotNull(restService.getApiService());

        Robolectric.setupActivity(LoginActivity.class);
    }
}