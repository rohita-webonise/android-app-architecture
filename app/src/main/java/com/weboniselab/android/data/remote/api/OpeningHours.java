package com.weboniselab.android.data.remote.api;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rohit.anvekar on 5/19/2017.
 */

public class OpeningHours implements Parcelable {

    @SerializedName("open_now")
    @Expose
    private Boolean openNow;

    public Boolean getOpenNow() {
        return openNow;
    }

    public void setOpenNow(Boolean openNow) {
        this.openNow = openNow;
    }

    @Override
    public String toString() {
        return "OpeningHours{" +
                "openNow=" + openNow +
                '}';
    }

    protected OpeningHours(Parcel in) {
        byte openNowVal = in.readByte();
        openNow = openNowVal == 0x02 ? null : openNowVal != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (openNow == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (openNow ? 0x01 : 0x00));
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<OpeningHours> CREATOR = new Parcelable.Creator<OpeningHours>() {
        @Override
        public OpeningHours createFromParcel(Parcel in) {
            return new OpeningHours(in);
        }

        @Override
        public OpeningHours[] newArray(int size) {
            return new OpeningHours[size];
        }
    };
}