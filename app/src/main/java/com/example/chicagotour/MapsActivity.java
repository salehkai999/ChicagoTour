package com.example.chicagotour;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.chicagotour.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.Polyline;

import java.util.ArrayList;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private static final int LOCATION_REQUEST = 111;
    private static final int ACCURACY_REQUEST = 222;
    private static final String TAG = "MapsAcitvity";
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Polyline llHistoryPolyline;
    private final ArrayList<LatLng> latLonHistory = new ArrayList<>();
    private boolean zooming = false;
    private float oldZoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if(checkPermission()){
            Log.d(TAG, "checkPermission: GRANTED");
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    private boolean checkPermission() {
        ArrayList<String> perms = new ArrayList<>();
        Log.d(TAG, "checkPermission: ");
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            perms.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED) {
                perms.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION);
            }
        }

        if (!perms.isEmpty()) {
            String[] array = perms.toArray(new String[0]);
            ActivityCompat.requestPermissions(this,
                    array, LOCATION_REQUEST);
            return false;
        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LOCATION_REQUEST) {
            int permCount = permissions.length;
            int permSum = 0;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    permSum++;
                } else {
                    sb.append(permissions[i]).append(", ");
                }
            }
            if (permSum == permCount) {
                Toast.makeText(this, "GRANTED!!", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onRequestPermissionsResult: GRANTED");
            } else {
                Toast.makeText(this,
                        "Required permissions not granted: " + sb.toString(),
                        Toast.LENGTH_LONG).show();
            }
        }
    }

}