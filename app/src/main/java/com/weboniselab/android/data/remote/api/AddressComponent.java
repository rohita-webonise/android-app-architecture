package com.weboniselab.android.data.remote.api;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rohit.anvekar on 5/21/2017.
 */

public class AddressComponent implements Parcelable {

    @SerializedName("long_name")
    @Expose
    private String longName;
    @SerializedName("short_name")
    @Expose
    private String shortName;
    @SerializedName("types")
    @Expose
    private List<String> types = null;

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }


    protected AddressComponent(Parcel in) {
        longName = in.readString();
        shortName = in.readString();
        if (in.readByte() == 0x01) {
            types = new ArrayList<>();
            in.readList(types, String.class.getClassLoader());
        } else {
            types = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(longName);
        dest.writeString(shortName);
        if (types == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(types);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<AddressComponent> CREATOR = new Parcelable.Creator<AddressComponent>() {
        @Override
        public AddressComponent createFromParcel(Parcel in) {
            return new AddressComponent(in);
        }

        @Override
        public AddressComponent[] newArray(int size) {
            return new AddressComponent[size];
        }
    };
}