package com.mike.givemewingzz.mapsclusterdemo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mike.givemewingzz.mapsclusterdemo.R;
import com.mike.givemewingzz.mapsclusterdemo.adapters.realmutils.RealmBaseRecyclerViewAdapter;
import com.mike.givemewingzz.mapsclusterdemo.model.data.Geometry;
import com.mike.givemewingzz.mapsclusterdemo.model.data.Location;
import com.mike.givemewingzz.mapsclusterdemo.model.data.OpeningHours;
import com.mike.givemewingzz.mapsclusterdemo.model.data.Results;
import com.mike.givemewingzz.mapsclusterdemo.model.data.ViewPort;
import com.squareup.picasso.Picasso;

import io.realm.RealmResults;

public class LocationListAdapter extends RealmBaseRecyclerViewAdapter<Results, LocationListAdapter.MainViewHolder> {

    public static final String TAG = LocationListAdapter.class.getSimpleName();

    public Context context;
    public EventListener eventListener;

    public RealmResults<Results> resultsRealmResults;

    public LocationListAdapter(Context context, RealmResults<Results> realmResults, boolean automaticUpdate) {
        super(context, realmResults, automaticUpdate);
        this.resultsRealmResults = realmResults;
        this.context = context;
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.two_row_staggerred_layout, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, final int position) {

        final Results results = getItem(position);
        final Geometry geometry = results.getGeometry();
        final Location location = geometry.getPlaceLocation();
        final ViewPort viewPort = geometry.getViewPort();

        final OpeningHours openingHours = results.getOpeningHours();

        Log.d(TAG, " Geometry Location : lat : "
                + location.getLatitude() + ": lon : "
                + location.getLongitude());

        Log.d(TAG, " Geometry ViewPort : lat NE : "
                + viewPort.getNorthEast().getNorthEastlatitude() + ": lon SW : "
                + viewPort.getSouthWest().getSouthWestLatitude());

        String rating = results.getRating();

        holder.locationName.setText(results.getLocationName());
        holder.txFormattedAddress.setText(results.getFormattedAddress());
        holder.locationGeo.setText("Latitude : \n" + location.getLatitude() + "\n\nLongitude : \n" + location.getLongitude());
        Picasso.with(context).load(results.getIcon()).into(holder.locationImage);

        if (rating != null) {

            if (!rating.equals("null")) {
                holder.locationRating.setText("Location Rating  :  " + results.getRating());
            } else {
                holder.locationRating.setText("Location Rating Not Available");
            }

        } else {
            holder.locationRating.setText("Location Rating Not Available");
        }

        holder.detailsContiner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventListener.onItemClick(position, results);
            }
        });

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public Results getItem(int i) {
        return resultsRealmResults.get(i);
    }

    @Override
    public int getItemCount() {
        return resultsRealmResults.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        TextView txFormattedAddress;
        ImageView locationImage;
        TextView locationGeo;
        TextView locationName;
        TextView locationRating;
        LinearLayout detailsContiner;


        public MainViewHolder(View view) {
            super(view);
            txFormattedAddress = (TextView) view.findViewById(R.id.formatted_address);
            locationName = (TextView) view.findViewById(R.id.locationName);
            locationGeo = (TextView) view.findViewById(R.id.locationGeo);
            locationImage = (ImageView) view.findViewById(R.id.locationIcon);
            detailsContiner = (LinearLayout) view.findViewById(R.id.locationContainer);
            locationRating = (TextView) view.findViewById(R.id.locationRating);
        }

    }

    public interface EventListener {
        void onItemClick(final int position, Results results);
    }

}
