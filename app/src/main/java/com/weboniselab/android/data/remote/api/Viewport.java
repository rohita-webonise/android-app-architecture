package com.weboniselab.android.data.remote.api;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rohit.anvekar on 5/21/2017.
 */

public class Viewport implements Parcelable {

    @SerializedName("northeast")
    @Expose
    private Northeast northeast;
    @SerializedName("southwest")
    @Expose
    private Southwest southwest;

    public Northeast getNortheast() {
        return northeast;
    }

    public void setNortheast(Northeast northeast) {
        this.northeast = northeast;
    }

    public Southwest getSouthwest() {
        return southwest;
    }

    public void setSouthwest(Southwest southwest) {
        this.southwest = southwest;
    }


    protected Viewport(Parcel in) {
        northeast = (Northeast) in.readValue(Northeast.class.getClassLoader());
        southwest = (Southwest) in.readValue(Southwest.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(northeast);
        dest.writeValue(southwest);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Viewport> CREATOR = new Parcelable.Creator<Viewport>() {
        @Override
        public Viewport createFromParcel(Parcel in) {
            return new Viewport(in);
        }

        @Override
        public Viewport[] newArray(int size) {
            return new Viewport[size];
        }
    };
}
