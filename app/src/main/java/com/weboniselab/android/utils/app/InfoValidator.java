package com.weboniselab.android.utils.app;

import java.util.Collection;
import java.util.regex.Pattern;


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
        return null != valueToCheck && !valueToCheck.trim().equals("");
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
        Pattern emailPattern = Pattern.compile(AppConstants.Patterns.EMAIL_PATTERN);

        return (isNotNullOrBlank(val) && emailPattern.matcher(val).matches());
    }


    /**
     * Method to check whether password is valid as per regex mention on AppConstants
     *
     * @param password
     * @return
     */
    public static boolean isValidPassword(String password) {
        Pattern passwordPattern = Pattern.compile(AppConstants.Patterns.PASSWORD_PATTERN);
        if (!isNotNullOrBlank(password)) {
            return false;
        } else if (password.length() < 6 || password.length() > 20) {
            return false;
        } else if (!passwordPattern.matcher(password).matches()) {
            return false;
        }
        return true;
    }


    public static boolean isValidZipCode(String zipCode) {
        return isNotNullOrBlank(zipCode) &&
                zipCode.length() >= MIN_ZIP_CODE_LENGTH &&
                zipCode.length() <= MAX_ZIP_CODE_LENGTH;
    }

    public static boolean isValidMobileNumber(String mobileNumber) {
        final String REGEX_PATTERN_CONTACT_NUMBER =
                "\\+?(\\d{10,15}|\\(?\\d{3}\\)?[-.\\s]\\d{3}[-.\\s]\\d{4})";

        return isNotNullOrBlank(mobileNumber) && Pattern.compile(REGEX_PATTERN_CONTACT_NUMBER)
                .matcher(mobileNumber).matches();
    }


    /**
     * Method to check whether login credentials are valid as per rule define for the application.
     *
     * @param email
     * @param password
     * @return
     */
    public static boolean isValidLogin(String email, String password) {
        return isValidEmail(email) && isValidPassword(password);
    }
}
