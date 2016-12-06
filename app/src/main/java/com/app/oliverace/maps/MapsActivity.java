package com.app.oliverace.maps;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.app.oliverace.util.LocationService;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener,ActivityCompat.OnRequestPermissionsResultCallback {

    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    TextView txtLat;
    String lat;
    String provider;
    protected String latitude, longitude;
    protected boolean gps_enabled, network_enabled;
    protected double currentLat;
    protected double currentLong;
    GoogleMap mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean permissionGranted = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        setContentView(R.layout.activity_maps);
        txtLat = (TextView) findViewById(R.id.textview1);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria cri= new Criteria();

        String bbb = locationManager.getBestProvider(cri, true);
        if(bbb == null){
          Toast.makeText(this, R.string.error_permission_map, Toast.LENGTH_LONG).show();}
       // }else {
            Location myLocation = locationManager.getLastKnownLocation(bbb);
            currentLat = myLocation.getLatitude();
            currentLong = myLocation.getLongitude();



        //System.out.println(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION));
        //if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
          //      PackageManager.PERMISSION_GRANTED) {
      //  } else {
         //   Toast.makeText(this, R.string.error_permission_map, Toast.LENGTH_LONG).show();
      //  }
        if(permissionGranted) {
            // {Some Code}
            Toast.makeText(this, R.string.ok_permission_map, Toast.LENGTH_LONG).show();
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        }


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap map) {
        // Add a marker in Sydney, Australia, and move the camera.
        //  LatLng sydney = new LatLng(-34, 151);
        //  map.addMarker(new MarkerOptions()..position(sydney).title("Marker in Sydney"));
        //   map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
        };
        map.setIndoorEnabled(false);
        map.setBuildingsEnabled(false);

        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
               map.addMarker(new MarkerOptions().position(latLng));
            }
        });
        map.setMapType(1);
        LatLng latlong = new LatLng(currentLat, currentLong);

        map.getUiSettings().setMyLocationButtonEnabled(true);
        //map.addMarker(new MarkerOptions().position(latlong).title("Where am I."));
        CameraUpdate cameraPosition = CameraUpdateFactory.newLatLngZoom(latlong, 17);
        map.moveCamera(cameraPosition);
        map.animateCamera(cameraPosition);
        mapa = map;

    }




    @Override
    public void onLocationChanged(Location location) {
        txtLat = (TextView) findViewById(R.id.textview1);
        txtLat.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
        currentLat = location.getLatitude();
        currentLong = location.getLongitude();
        LatLng latlong = new LatLng(currentLat, currentLong);
        CameraUpdate cameraPosition = CameraUpdateFactory.newLatLngZoom(latlong, 17);
        mapa.moveCamera(cameraPosition);
        //Toast.makeText(this, "moved!", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 200: {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // {Some Code}
                }
            }
        }
    }

}
