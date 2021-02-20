package com.example.chicagotour;

import android.location.Location;
import android.location.LocationListener;
import android.util.Log;

import androidx.annotation.NonNull;

public class LocListener implements LocationListener {

    private final MapsActivity mapsActivity;
    private static final String TAG = "LocListener";

    public LocListener(MapsActivity mActivity){
        this.mapsActivity = mActivity;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Log.d(TAG, "onLocationChanged: " + location);
        mapsActivity.updateLocation(location);
    }
}
