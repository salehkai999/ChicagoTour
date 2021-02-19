package com.example.chicagotour.fences;

import com.example.chicagotour.MapsActivity;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.LocationServices;

public class FenceManager {

    private static final String TAG = "FenceManager";
    private final MapsActivity mapsActivity;
    private final GeofencingClient geofencingClient;

    public FenceManager(final MapsActivity mapsActivity) {
        this.mapsActivity = mapsActivity;
        geofencingClient = LocationServices.getGeofencingClient(mapsActivity);



    }
}
