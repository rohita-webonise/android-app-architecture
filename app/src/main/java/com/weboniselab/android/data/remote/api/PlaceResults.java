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
public class PlaceResults implements Parcelable {


    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "PlaceResults{" +
                "results=" + results +
                ", status='" + status + '\'' +
                '}';
    }

    protected PlaceResults(Parcel in) {
        if (in.readByte() == 0x01) {
            results = new ArrayList<>();
            in.readList(results, Result.class.getClassLoader());
        } else {
            results = null;
        }
        status = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (results == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(results);
        }
        dest.writeString(status);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PlaceResults> CREATOR = new Parcelable.Creator<PlaceResults>() {
        @Override
        public PlaceResults createFromParcel(Parcel in) {
            return new PlaceResults(in);
        }

        @Override
        public PlaceResults[] newArray(int size) {
            return new PlaceResults[size];
        }
    };
}