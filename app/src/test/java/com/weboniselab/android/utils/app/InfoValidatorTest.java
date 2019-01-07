package com.weboniselab.android.utils.app;

import android.text.TextUtils;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;

import org.junit.Test;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by rohit.anvekar on 7/1/19.
 */
public class InfoValidatorTest {

    private final String  validEmail = "xyx@gmail.com";
    private final String  validPassword = "test123@#";
    private final String  invalidEmail = "xyx@gmail@.com";
    private final String  invalidPassword = "12345";
    private final String  invalidMobileNumber = "123456789";
    private final String  invalidMobileNumber1 = "dsad213";
    private final String  validMobileNumber = "8976276372";
    private final String  invalidZipCode = "123";
    private final String  validZipCode = "41105";


    @Test
    public void isNotNullOrBlank() {

        Assert.assertFalse(InfoValidator.isNotNullOrBlank(null));
        Assert.assertFalse(InfoValidator.isNotNullOrBlank(""));
        Assert.assertTrue(InfoValidator.isNotNullOrBlank("test"));

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

        Assert.assertFalse(InfoValidator.isValidEmail(""));

        Assert.assertFalse(InfoValidator.isValidEmail(invalidEmail));

        Assert.assertTrue(InfoValidator.isValidEmail(validEmail));
    }

    @Test
    public void isValidPassword() {

        Assert.assertFalse(InfoValidator.isValidPassword(null));

        Assert.assertFalse(InfoValidator.isValidPassword(""));

        Assert.assertFalse(InfoValidator.isValidPassword(invalidPassword));

        Assert.assertTrue(InfoValidator.isValidPassword(validPassword));
    }


    @Test
    public void isValidZipCode() {

        Assert.assertFalse(InfoValidator.isValidZipCode(null));

        Assert.assertFalse(InfoValidator.isValidZipCode(""));

        Assert.assertFalse(InfoValidator.isValidZipCode(invalidZipCode));

        Assert.assertTrue(InfoValidator.isValidZipCode(validZipCode));
    }

    @Test
    public void isValidMobileNumber() {

        Assert.assertFalse(InfoValidator.isValidMobileNumber(null));
        Assert.assertFalse(InfoValidator.isValidMobileNumber(""));
        Assert.assertFalse(InfoValidator.isValidMobileNumber(invalidMobileNumber));
        Assert.assertFalse(InfoValidator.isValidMobileNumber(invalidMobileNumber1));
        Assert.assertTrue(InfoValidator.isValidMobileNumber(validMobileNumber));
    }

    @Test
    public void isValidLogin() {

        Assert.assertFalse(InfoValidator.isValidLogin(null,null));
        Assert.assertFalse(InfoValidator.isValidLogin(null,""));
        Assert.assertFalse(InfoValidator.isValidLogin("",null));

        Assert.assertFalse(InfoValidator.isValidLogin("",""));
        Assert.assertFalse(InfoValidator.isValidLogin(invalidEmail,invalidPassword));
        Assert.assertFalse(InfoValidator.isValidLogin("",validPassword));
        Assert.assertFalse(InfoValidator.isValidLogin(validEmail,""));

        Assert.assertFalse(InfoValidator.isValidLogin(validEmail,invalidPassword));
        Assert.assertFalse(InfoValidator.isValidLogin(invalidEmail,validPassword));

        Assert.assertTrue(InfoValidator.isValidLogin(validEmail,validPassword));
    }
}