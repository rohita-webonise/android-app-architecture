package com.weboniselab.android;

import android.text.TextUtils;
import android.util.Log;

import com.weboniselab.android.data.DataManager;
import com.weboniselab.android.data.remote.ApiService;
import com.weboniselab.android.di.component.AppComponent;
import com.weboniselab.android.di.module.AppModule;
import com.weboniselab.android.ui.story.login.LoginNavigator;
import com.weboniselab.android.ui.story.login.LoginViewModel;
import com.weboniselab.android.utils.app.InfoValidator;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.rule.PowerMockRule;

import javax.inject.Inject;

import io.reactivex.schedulers.TestScheduler;
import it.cosenonjaviste.daggermock.DaggerMockRule;

import static org.mockito.Mockito.verify;

/**
 * Created by rohit.anvekar on 25/4/18.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({TextUtils.class,InfoValidator.class})
public class LoginTestCase {

    @Mock
    LoginNavigator mLoginCallback;
    @InjectMocks
    DataManager mMockDataManager;


    private LoginViewModel mLoginViewModel;
    private TestScheduler mTestScheduler;



    @BeforeClass
    public static void onlyOnce() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(TextUtils.class);
        PowerMockito.mockStatic(InfoValidator.class);
        mTestScheduler = new TestScheduler();
        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider(mTestScheduler);
        mLoginViewModel = new LoginViewModel(mMockDataManager, testSchedulerProvider);
        mLoginViewModel.setNavigator(mLoginCallback);
    }

    @After
    public void tearDown() throws Exception {
        mTestScheduler = null;
        mLoginViewModel = null;
        mLoginCallback = null;
    }

    @Test
    public void testCredentials() {
        String email = "dummy@gmail.com";
        String password = "password";
        Assert.assertEquals(email,email);
        Assert.assertEquals(password,password);
    }

    @Test
    public void testLogin() {
        String email = "dummy@gmail.com";
        String password = "password";
        Assert.assertFalse(mLoginViewModel.doLogin(email,password));
        //verify(mLoginCallback).gotoHome();
    }

    @Test
    public void testDependency() {

        Assert.assertNotNull(mMockDataManager);
        Assert.assertNotNull(mMockDataManager.getApiService());
        //verify(mLoginCallback).gotoHome();
    }

}
