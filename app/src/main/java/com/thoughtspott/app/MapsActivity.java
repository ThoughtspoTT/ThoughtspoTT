package com.thoughtspott.app;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Timestamp;
import java.util.HashMap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);
        assert mapFragment != null;

        mapFragment.getMapAsync(this);

        client = LocationServices.getFusedLocationProviderClient(this);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Sessions");


    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng Lubbock = new LatLng(33.581410,  -101.876037);
        LatLng test = new LatLng(33.583644, -101.876275);
        mMap.addMarker(new MarkerOptions().position(Lubbock).title("Physics").snippet("Created by Jacob, 10/31/2020 2:00 PM, Study for midterm"));
        mMap.addMarker(new MarkerOptions().position(test).title("Chemistry").snippet("Created by Ana, 11/2/2020 5:00 PM, Homework 2"));




        @SuppressLint("MissingPermission") Task<Location> task = client.getLastLocation();

        task.addOnSuccessListener(location -> {

            if (location != null){
                LatLng latLng = new LatLng(location.getLatitude()
                        ,location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(latLng).title("Location").snippet("You are here"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );

            }
            else
            {
                mMap.addMarker(new MarkerOptions().position(Lubbock).title("Pls").snippet("You absolute fool"));
            }
        });
    }
}