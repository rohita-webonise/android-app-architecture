package com.weboniselab.android.data.remote.api;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rohit.anvekar on 5/22/2017.
 */
public class PlacePhotoResult implements Parcelable {

    @SerializedName("html_attributions")
    @Expose
    private List<Object> htmlAttributions = null;
    @SerializedName("result")
    @Expose
    private Result result;
    @SerializedName("status")
    @Expose
    private String status;

    public List<Object> getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(List<Object> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "PlacePhotoResult{" +
                "htmlAttributions=" + htmlAttributions +
                ", result=" + result +
                ", status='" + status + '\'' +
                '}';
    }

    protected PlacePhotoResult(Parcel in) {
        if (in.readByte() == 0x01) {
            htmlAttributions = new ArrayList<>();
            in.readList(htmlAttributions, Object.class.getClassLoader());
        } else {
            htmlAttributions = null;
        }
        result = (Result) in.readValue(Result.class.getClassLoader());
        status = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (htmlAttributions == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(htmlAttributions);
        }
        dest.writeValue(result);
        dest.writeString(status);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PlacePhotoResult> CREATOR = new Parcelable.Creator<PlacePhotoResult>() {
        @Override
        public PlacePhotoResult createFromParcel(Parcel in) {
            return new PlacePhotoResult(in);
        }

        @Override
        public PlacePhotoResult[] newArray(int size) {
            return new PlacePhotoResult[size];
        }
    };
}