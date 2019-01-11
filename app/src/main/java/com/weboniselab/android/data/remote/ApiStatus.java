package com.weboniselab.android.data.remote;

/**
 * Created by rohit.anvekar on 2/1/19.
 */
public class ApiStatus {


    public enum ApiCallStatus {
            SUCCESS , FAILURE , ERROR;
    }

    private int code;

    private ApiCallStatus statusCode;

    private boolean isSuccess;
    private boolean isBodyAvailable;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }



    public ApiCallStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(ApiCallStatus statusCode) {
        this.statusCode = statusCode;
    }


    public boolean isBodyAvailable() {
        return isBodyAvailable;
    }

    public void setBodyAvailable(boolean bodyAvailable) {
        isBodyAvailable = bodyAvailable;
    }


}
