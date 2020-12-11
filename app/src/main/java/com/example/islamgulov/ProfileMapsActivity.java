package com.example.islamgulov;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.widget.TextView;

public class ProfileMapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private TextView LatitudeTextView, LongitudeTextView;

    private GoogleMap mMap;

    public ProfileMapsActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Init();
    }

    private void Init() {
        LatitudeTextView = findViewById(R.id.LatitudeTextView);
        LongitudeTextView = findViewById(R.id.LongitudeTextView);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
              ActivityCompat.requestPermissions(ProfileMapsActivity.this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123); }



        }
        mMap.setMyLocationEnabled(true);
             mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                 @Override
                 public void onMyLocationChange(Location location) {
                     if (location != null) {
                         LatitudeTextView = setText(String.valueOf(location.getLatitude()));
                         LongitudeTextView = setText(String.valueOf(location.getLongitude()));
                     }
                 }

             });
         }}






