package com.mike.givemewingzz.mapsclusterdemo.service;

import com.mike.givemewingzz.mapsclusterdemo.model.data.BaseModel;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Interface for creating requests
 */

public interface RetrofitInterface {

    // Use this api for passing in your own location(lat, lon)
    @GET("/maps/api/place/textsearch/json?query={searchType}&location={latitude},{longitude}&radius={radiusCover}&key= AIzaSyDk35iL27MteP2TYK7Wes13P4knEX1sFsk ")
    Call<BaseModel> getBBVALocationsWithQuery(@Query("searchType") String searchType, @Query("latitude") String latitude, @Query("longitude") String longitude, @Query("radius") int radius, @Query("type") String type, @Query("location") String location);

    @GET("/maps/api/place/textsearch/json?query=BBVACompass&location=MY_LAT,MY_LAT&radius=10000")
    Call<BaseModel> getBBVALocations(@Query("key") String API_KEY);

}
