package com.example.pets;

import android.os.Parcel;
import android.os.Parcelable;

public class Pet implements Parcelable {
    private String name;
    private String description;
    private String ownerName;
    private String phoneNumber;
    private int imageId;

    public Pet(String name, String description, String ownerName, String phoneNumber, int imageId) {
        this.name = name;
        this.description = description;
        this.ownerName = ownerName;
        this.phoneNumber = phoneNumber;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    protected Pet(Parcel in) {
        name = in.readString();
        description = in.readString();
        ownerName = in.readString();
        phoneNumber = in.readString();
        imageId = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(ownerName);
        dest.writeString(phoneNumber);
        dest.writeInt(imageId);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Pet> CREATOR = new Parcelable.Creator<Pet>() {
        @Override
        public Pet createFromParcel(Parcel in) {
            return new Pet(in);
        }

        @Override
        public Pet[] newArray(int size) {
            return new Pet[size];
        }
    };
}
