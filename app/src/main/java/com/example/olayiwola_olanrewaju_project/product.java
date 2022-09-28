package com.example.olayiwola_olanrewaju_project;

import android.os.Parcel;
import android.os.Parcelable;

public class product implements Parcelable {
    String image,image_two,name,description;
    Float price;
    Integer count = 1;
    public product() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public product(String image, String image_two, String name, String description, Float price) {
        this.image = image;
        this.image_two = image_two;
        this.name = name;
        this.description = description;
        this.price = price;

    }

    protected product(Parcel in) {
        image = in.readString();
        image_two = in.readString();
        name = in.readString();
        description = in.readString();
        count = in.readInt();
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readFloat();
        }
    }

    public static final Creator<product> CREATOR = new Creator<product>() {
        @Override
        public product createFromParcel(Parcel in) {
            return new product(in);
        }

        @Override
        public product[] newArray(int size) {
            return new product[size];
        }
    };

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage_two() {
        return image_two;
    }

    public void setImage_two(String image_two) {
        this.image_two = image_two;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(image);
        dest.writeString(image_two);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(count);
        if (price == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(price);
        }
    }
}
