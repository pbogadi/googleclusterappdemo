package com.mike.givemewingzz.mapsclusterdemo.model.data;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class NorthEast extends RealmObject {

    @SerializedName("lat")
    private Double northEastlatitude;

    @SerializedName("lng")
    private Double northEastLongitude;

    public Double getNorthEastlatitude() {
        return northEastlatitude;
    }

    public void setNorthEastlatitude(Double northEastlatitude) {
        this.northEastlatitude = northEastlatitude;
    }

    public Double getNorthEastLongitude() {
        return northEastLongitude;
    }

    public void setNorthEastLongitude(Double northEastLongitude) {
        this.northEastLongitude = northEastLongitude;
    }

}
