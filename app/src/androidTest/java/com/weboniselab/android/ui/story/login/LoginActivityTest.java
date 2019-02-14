package com.weboniselab.android.ui.story.login;

import com.weboniselab.android.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by rohit.anvekar on 7/1/19.
 */
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {
    private static final String VALID_EMAIL = "xyx@gmail.com";
    private static final String INVALID_EMAIL = "xyx@gmail@.com";
    private static final String VALID_PASSWORD = "test123@#";
    private static final String INVALID_PASSWORD = "12345";

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void setUp() throws Exception {
        Intents.init();

    }

    @Test
    public void doTestLoginWithInvalidCreds(){
        onView(withId(R.id.edtUserName)).perform(typeText(INVALID_EMAIL));
        onView(withId(R.id.edtPassword)).perform(typeText(INVALID_PASSWORD));
        onView(withId(R.id.btnSignIn)).perform(click());
    }

    @Test
    public void doTestLoginWithValidCreds(){
        onView(withId(R.id.edtUserName)).perform(typeText(VALID_EMAIL));
        onView(withId(R.id.edtPassword)).perform(typeText(VALID_PASSWORD));

        onView(withId(R.id.btnSignIn)).perform(click());
        //Intents.intended(IntentMatchers.hasComponent(HomeActivity.class.getName()));

        onView(withId(R.id.progressBar)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.homeTxt)).check(ViewAssertions.matches(isDisplayed()));//check(ViewAssertions.matches(withText("NewText")));

    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }
}