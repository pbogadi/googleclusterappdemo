package com.mike.givemewingzz.mapsclusterdemo.model.data;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Geometry extends RealmObject {


    @SerializedName("location")
    private Location placeLocation;

    @SerializedName("viewport")
    private ViewPort viewPort;

    public Location getPlaceLocation() {
        return placeLocation;
    }

    public void setPlaceLocation(Location placeLocation) {
        this.placeLocation = placeLocation;
    }

    public ViewPort getViewPort() {
        return viewPort;
    }

    public void setViewPort(ViewPort viewPort) {
        this.viewPort = viewPort;
    }

}
