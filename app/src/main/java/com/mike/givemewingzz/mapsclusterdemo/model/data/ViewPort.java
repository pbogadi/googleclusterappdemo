package com.mike.givemewingzz.mapsclusterdemo.model.data;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class ViewPort extends RealmObject {

    @SerializedName("northeast")
    private NorthEast northEast;

    @SerializedName("southwest")
    private SouthWest southWest;

    public NorthEast getNorthEast() {
        return northEast;
    }

    public void setNorthEast(NorthEast northEast) {
        this.northEast = northEast;
    }

    public SouthWest getSouthWest() {
        return southWest;
    }

    public void setSouthWest(SouthWest southWest) {
        this.southWest = southWest;
    }

}
