package com.weboniselab.android.utils.app;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rohit.anvekar on 7/1/19.
 */
public class InfoValidatorTest {

    private static final String VALID_EMAIL = "xyx@gmail.com";
    private static final String INVALID_EMAIL = "xyx@gmail@.com";
    private static final String VALID_PASSWORD = "test123@#";
    private static final String INVALID_PASSWORD = "12345";
    private static final String INVALID_MOBILE_NUMBER = "123456789";
    private static final String INVALID_MOBILE_NUMBER_1 = "dsad213";
    private static final String VALID_MOBILE_NUMBER = "8976276372";
    private static final String INVALID_ZIP_CODE = "123";
    private static final String VALID_ZIP_CODE = "41105";
    private static final String TEST_STRING = "TEST_STRING";
    private static final String EMPTY_STRING = "";


    @Test
    public void isNotNullOrBlank() {

        Assert.assertFalse(InfoValidator.isNotNullOrBlank(null));
        Assert.assertFalse(InfoValidator.isNotNullOrBlank(EMPTY_STRING));
        Assert.assertTrue(InfoValidator.isNotNullOrBlank(TEST_STRING));

    }

    @Test
    public void isValidCollection() {

        List<String> list = null;
        Assert.assertFalse(InfoValidator.isValidCollection(list));
        list = new ArrayList<>();
        Assert.assertFalse(InfoValidator.isValidCollection(list));
        list.add("Test");
        Assert.assertTrue(InfoValidator.isValidCollection(list));
    }

    @Test
    public void isValidEmail() {

        Assert.assertFalse(InfoValidator.isValidEmail(null));

        Assert.assertFalse(InfoValidator.isValidEmail(EMPTY_STRING));

        Assert.assertFalse(InfoValidator.isValidEmail(INVALID_EMAIL));

        Assert.assertTrue(InfoValidator.isValidEmail(VALID_EMAIL));
    }

    @Test
    public void isValidPassword() {

        Assert.assertFalse(InfoValidator.isValidPassword(null));

        Assert.assertFalse(InfoValidator.isValidPassword(EMPTY_STRING));

        Assert.assertFalse(InfoValidator.isValidPassword(INVALID_PASSWORD));

        Assert.assertTrue(InfoValidator.isValidPassword(VALID_PASSWORD));
    }


    @Test
    public void isValidZipCode() {

        Assert.assertFalse(InfoValidator.isValidZipCode(null));

        Assert.assertFalse(InfoValidator.isValidZipCode(EMPTY_STRING));

        Assert.assertFalse(InfoValidator.isValidZipCode(INVALID_ZIP_CODE));

        Assert.assertTrue(InfoValidator.isValidZipCode(VALID_ZIP_CODE));
    }

    @Test
    public void isValidMobileNumber() {

        Assert.assertFalse(InfoValidator.isValidMobileNumber(null));
        Assert.assertFalse(InfoValidator.isValidMobileNumber(EMPTY_STRING));
        Assert.assertFalse(InfoValidator.isValidMobileNumber(INVALID_MOBILE_NUMBER));
        Assert.assertFalse(InfoValidator.isValidMobileNumber(INVALID_MOBILE_NUMBER_1));
        Assert.assertTrue(InfoValidator.isValidMobileNumber(VALID_MOBILE_NUMBER));
    }

    @Test
    public void isValidLogin() {

        Assert.assertFalse(InfoValidator.isValidLogin(null,null));
        Assert.assertFalse(InfoValidator.isValidLogin(null, EMPTY_STRING));
        Assert.assertFalse(InfoValidator.isValidLogin(EMPTY_STRING,null));

        Assert.assertFalse(InfoValidator.isValidLogin(EMPTY_STRING, EMPTY_STRING));
        Assert.assertFalse(InfoValidator.isValidLogin(INVALID_EMAIL, INVALID_PASSWORD));
        Assert.assertFalse(InfoValidator.isValidLogin(EMPTY_STRING, VALID_PASSWORD));
        Assert.assertFalse(InfoValidator.isValidLogin(VALID_EMAIL, EMPTY_STRING));

        Assert.assertFalse(InfoValidator.isValidLogin(VALID_EMAIL, INVALID_PASSWORD));
        Assert.assertFalse(InfoValidator.isValidLogin(INVALID_EMAIL, VALID_PASSWORD));

        Assert.assertTrue(InfoValidator.isValidLogin(VALID_EMAIL, VALID_PASSWORD));
    }
}