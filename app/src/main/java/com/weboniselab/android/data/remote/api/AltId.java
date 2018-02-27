package com.weboniselab.android.data.remote.api;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by rohit.anvekar on 5/19/2017.
 */

public class AltId implements Parcelable {

    @SerializedName("place_id")
    @Expose
    private String placeId;
    @SerializedName("scope")
    @Expose
    private String scope;

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "AltId{" +
                "placeId='" + placeId + '\'' +
                ", scope='" + scope + '\'' +
                '}';
    }

    protected AltId(Parcel in) {
        placeId = in.readString();
        scope = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(placeId);
        dest.writeString(scope);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<AltId> CREATOR = new Parcelable.Creator<AltId>() {
        @Override
        public AltId createFromParcel(Parcel in) {
            return new AltId(in);
        }

        @Override
        public AltId[] newArray(int size) {
            return new AltId[size];
        }
    };
}