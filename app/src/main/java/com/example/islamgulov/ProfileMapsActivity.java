package com.example.islamgulov;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.widget.TextView;

import static com.example.islamgulov.Transform.getRoundedMapBitmap;
import static com.example.islamgulov.UserStaticInfo.ActiveUser;
import static com.example.islamgulov.UserStaticInfo.POSITION_LATITUDE;
import static com.example.islamgulov.UserStaticInfo.POSITION_LONGITUDE;
import static com.example.islamgulov.UserStaticInfo.USERS_PROFILE_INFO;
import static com.example.islamgulov.UserStaticInfo.profileId;

public class ProfileMapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    FirebaseDatabase database;
    Location lastLocation;
    PolylineOptions rectOptions = new PolylineOptions(); Polyline polygon;



    private TextView LatitudeTextView, LongitudeTextView, NameTextView;

    private GoogleMap mMap;
    LayoutInflater inflater;
    GridView PhotosGridView;

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
        NameTextView.setText(ActiveUser.getName());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    LayoutInflater inflater;
    GridView PhotosGridview;

    private void Init() {
        PhotosGridView = findViewById(R.id.PhotosGridView);
        inflater= LayoutInflater.from(this);
        PhotosGridView.setAdapter(new PhotoGridAdatper);

        LatitudeTextView = findViewById(R.id.LatitudeTextView);
        LongitudeTextView = findViewById(R.id.LongitudeTextView);
        NameTextView = findViewById(R.id.NameTextView);

    }

    public class PhotoGridAdatper extends BaseAdapter {}

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
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
        {

            ActivityCompat.requestPermissions(ProfileMapsActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);
        }
        else
        {
            setMapLocationChange();
        }


    }

    private void setMapLocationChange() {
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                if (location !=null)
                {
                    mMap.clear();
                if (database == null)
                    database = FirebaseDatabase.getInstance();

                String Lat = String.valueOf(location.getLatitude());
                String Lon = String.valueOf(location.getLongitude());

                database.getReference(USERS_PROFILE_INFO).child(profileId).child(POSITION_LATITUDE).setValue(Lat);
                database.getReference(USERS_PROFILE_INFO).child(profileId).child(POSITION_LONGITUDE).setValue(Lon);
                    LatitudeTextView.setText(Lat);
                    LongitudeTextView.setText(Lon);
                    LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());

                    if (lastLocation != null)
                    {
                        if(polygon!=null)
                            polygon.remove();
                        rectOptions.add(new LatLng(location.getLatitude(),location.getLongitude()));
                        polygon =mMap.addPolyline(rectOptions);
                    }

                    else
                        rectOptions.add(new LatLng(location.getLatitude(),location.getLongitude()));
                    lastLocation = location;

                    Bitmap bitmap = BitmapFactory.decodeResouce(getResouces(),R.drawable.www);
                    mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.fromBitmap(getRoundedMapBitmap(bitmap))));
                }}

        });

    }
}











