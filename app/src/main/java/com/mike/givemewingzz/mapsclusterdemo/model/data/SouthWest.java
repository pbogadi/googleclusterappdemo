package com.mike.givemewingzz.mapsclusterdemo.model.data;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class SouthWest extends RealmObject {

    @SerializedName("lat")
    private Double southWestLatitude;

    @SerializedName("lng")
    private Double southWestLongitude;

    public Double getSouthWestLatitude() {
        return southWestLatitude;
    }

    public void setSouthWestLatitude(Double southWestLatitude) {
        this.southWestLatitude = southWestLatitude;
    }

    public Double getSouthWestLongitude() {
        return southWestLongitude;
    }

    public void setSouthWestLongitude(Double southWestLongitude) {
        this.southWestLongitude = southWestLongitude;
    }

}
