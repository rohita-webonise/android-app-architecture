package com.weboniselab.android.data.remote.api;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rohit.anvekar on 5/19/2017.
 */
public class Result implements Parcelable {
    @SerializedName("address_components")
    @Expose
    private List<AddressComponent> addressComponents = null;

    @SerializedName("formatted_address")
    @Expose
    private String formattedAddress;

    @SerializedName("geometry")
    @Expose
    private Geometry geometry;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("opening_hours")
    @Expose
    private OpeningHours openingHours;
    @SerializedName("photos")
    @Expose
    private List<Photo> photos = null;
    @SerializedName("place_id")
    @Expose
    private String placeId;
    @SerializedName("scope")
    @Expose
    private String scope;
    @SerializedName("alt_ids")
    @Expose
    private List<AltId> altIds = null;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("types")
    @Expose
    private List<String> types = null;
    @SerializedName("vicinity")
    @Expose
    private String vicinity;

    public List<AddressComponent> getAddressComponents() {
        return addressComponents;
    }

    public void setAddressComponents(List<AddressComponent> addressComponents) {
        this.addressComponents = addressComponents;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

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

    public List<AltId> getAltIds() {
        return altIds;
    }

    public void setAltIds(List<AltId> altIds) {
        this.altIds = altIds;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    @Override
    public String toString() {
        return "Result{" +
                "geometry=" + geometry +
                ", icon='" + icon + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", openingHours=" + openingHours +
                ", photos=" + photos +
                ", placeId='" + placeId + '\'' +
                ", scope='" + scope + '\'' +
                ", altIds=" + altIds +
                ", reference='" + reference + '\'' +
                ", types=" + types +
                ", vicinity='" + vicinity + '\'' +
                '}';
    }

    protected Result(Parcel in) {
        if (in.readByte() == 0x01) {
            addressComponents = new ArrayList<>();
            in.readList(addressComponents, AddressComponent.class.getClassLoader());
        } else {
            addressComponents = null;
        }
        formattedAddress = in.readString();
        geometry = (Geometry) in.readValue(Geometry.class.getClassLoader());
        icon = in.readString();
        id = in.readString();
        name = in.readString();
        openingHours = (OpeningHours) in.readValue(OpeningHours.class.getClassLoader());
        if (in.readByte() == 0x01) {
            photos = new ArrayList<Photo>();
            in.readList(photos, Photo.class.getClassLoader());
        } else {
            photos = null;
        }
        placeId = in.readString();
        scope = in.readString();
        if (in.readByte() == 0x01) {
            altIds = new ArrayList<AltId>();
            in.readList(altIds, AltId.class.getClassLoader());
        } else {
            altIds = null;
        }
        reference = in.readString();
        if (in.readByte() == 0x01) {
            types = new ArrayList<String>();
            in.readList(types, String.class.getClassLoader());
        } else {
            types = null;
        }
        vicinity = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (addressComponents == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(addressComponents);
        }
        dest.writeString(formattedAddress);
        dest.writeValue(geometry);
        dest.writeString(icon);
        dest.writeString(id);
        dest.writeString(name);
        dest.writeValue(openingHours);
        if (photos == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(photos);
        }
        dest.writeString(placeId);
        dest.writeString(scope);
        if (altIds == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(altIds);
        }
        dest.writeString(reference);
        if (types == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(types);
        }
        dest.writeString(vicinity);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Result> CREATOR = new Parcelable.Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };
}