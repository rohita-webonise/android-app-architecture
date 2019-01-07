
package com.weboniselab.android.utils.app;


public final class AppConstants {

    private AppConstants() {
        // This utility class is not publicly instantiable
    }

    public static final String STATUS_CODE_SUCCESS = "success";
    public static final String STATUS_CODE_FAILED = "failed";

    public static final int API_STATUS_CODE_LOCAL_ERROR = 0;

    public static final String DB_NAME = "testing.db";
    public static final String PREF_NAME = "testing_pref";

    public static final long NULL_INDEX = -1L;

    public static final String SEED_DATABASE_OPTIONS = "seed/options.json";
    public static final String SEED_DATABASE_QUESTIONS = "seed/questions.json";

    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";


    public class Patterns {
        public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        public static final String USERNAME_PATTERN = "^[a-zA-Z0-9]{3,20}$";
        public static final String NAME_PATTERN = "^(?!(\\S+[\\-']$))(?!(\\S*[\\-']{2}\\S*$))(?=\\S{1,35}$)([a-zA-Z]['\\-a-zA-Z]*)$";
        public static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[A-Za-z])(?!^[~!@#\\$%^&*()_+\\-=]*$)([a-zA-Z0-9~!@#\\$%^&*()_+\\-=]{6,20})$";
    }

}
