package com.weboniselab.android.utils.app;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

import java.util.Collection;
import java.util.regex.Pattern;

import retrofit2.Response;


/**
 * Created by rohit.anvekar on 26/4/18.
 */
public class InfoValidator {

    public static final int MIN_ZIP_CODE_LENGTH = 4;
    public static final int MAX_ZIP_CODE_LENGTH = MIN_ZIP_CODE_LENGTH + 1;

    /**
     * Method to check if the passed string in empty or not
     *
     * @param valueToCheck the string value
     * @return true if the string is not null or blank, false otherwise
     */
    public static boolean isNotNullOrBlank(String valueToCheck) {
        return null != valueToCheck && !TextUtils.isEmpty(valueToCheck.trim());
    }

    /**
     * Method to check if {@link Collection} is null or empty
     *
     * @param collection the Collection instance to be checked for non-empty
     * @return true if collection has at-least one item
     */
    public static boolean isValidCollection(Collection collection) {
        return null != collection && !collection.isEmpty();
    }

    /**
     * Method to check the supplied string is a valid email
     *
     * @param val string to be checked for email-validation
     * @return true if valid email, false otherwise
     */
    public static boolean isValidEmail(String val) {
        return isNotNullOrBlank(val) && Patterns.EMAIL_ADDRESS.matcher(val).matches();
    }

    public static boolean isValidResponse(Response response) {
        return null != response && response.isSuccessful() && null != response.body();
    }

    public static boolean isValidZipCode(String zipCode) {
        return !InfoValidator.isNotNullOrBlank(zipCode) ||
                zipCode.length() >= MIN_ZIP_CODE_LENGTH &&
                        zipCode.length() <= MAX_ZIP_CODE_LENGTH;
    }

    public static boolean isValidMobileNumber(String mobileNumber) {
        final String REGEX_PATTERN_CONTACT_NUMBER =
                "\\+?(\\d{10,15}|\\(?\\d{3}\\)?[-.\\s]\\d{3}[-.\\s]\\d{4})";

        return !isNotNullOrBlank(mobileNumber) || Pattern.compile(REGEX_PATTERN_CONTACT_NUMBER)
                .matcher(mobileNumber).matches();
    }
}
