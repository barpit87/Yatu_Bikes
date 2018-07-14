package com.arpitbhardwaj.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;

    public void onClickBookABike(View view)
    {
        EditText editText=(EditText)findViewById(R.id.editText);
        String searchedLocation=editText.getText().toString();
        if(searchedLocation == null || (searchedLocation != null && searchedLocation.trim().length() == 0)){
            Toast.makeText(this.getApplicationContext(),"Please Enter A Location to Search ",Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(this.getApplicationContext(), Main2Activity.class);
            startActivity(intent);
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                      mMap.setMyLocationEnabled(true);
                      mMap.getUiSettings().setMyLocationButtonEnabled(true);
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                    Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (lastKnownLocation != null)
                    {
                        Toast.makeText(this.getApplicationContext(),lastKnownLocation.getLatitude()+"    "+lastKnownLocation.getLongitude(),Toast.LENGTH_SHORT).show();
                    }
               }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void onSearch(View view)
    {

        EditText editText=(EditText)findViewById(R.id.editText);
        String searchedLocation=editText.getText().toString();
        List<Address> addressList=null;
        if(searchedLocation == null || (searchedLocation != null && searchedLocation.trim().length() == 0)){
            Toast.makeText(this.getApplicationContext(),"Please Enter A Location to Search ",Toast.LENGTH_SHORT).show();
        }

             else  if(searchedLocation != null || !searchedLocation.equals(""))
        {    mMap.clear();
            Geocoder geocoder=new Geocoder(this);
            try {
                addressList=geocoder.getFromLocationName(searchedLocation,1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address=addressList.get(0);
            LatLng latLng1= new LatLng(address.getLatitude(),address.getLongitude());
            double lat=address.getLatitude();
            double lng=address.getLongitude();
            String lat_string=Double.toString(lat);
            String lng_string=Double.toString(lng);
            Log.i("latitude and longitude ",lat_string  +"  " +  lng_string);
            mMap.addMarker(new MarkerOptions().position(latLng1).title("Your Location"));
            CameraUpdate location=CameraUpdateFactory.newLatLngZoom(latLng1,15);
            mMap.animateCamera(location);
            Toast.makeText(this.getApplicationContext(),"Latitude is "+lat_string+" &  longitude is  "+lng_string, Toast.LENGTH_SHORT).show();

        }

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
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                // Add a marker in user location and move the camera
                LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(userLocation).title("Your Location"));

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if (Build.VERSION.SDK_INT < 23) {
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            //Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            //double lat=lastKnownLocation.getLatitude();
            //double lng=lastKnownLocation.getLongitude();
            //String lat_string=Double.toString(lat);
            //String lng_string=Double.toString(lng);
            //Toast.makeText(MapsActivity.this,"location is"+lat_string +" "+lng_string,Toast.LENGTH_LONG).show();




        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                // Add a marker in user location and move the camera
                if (lastKnownLocation != null) {
                    LatLng userLocation = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
                    mMap.clear();
                    mMap.addMarker(new MarkerOptions().position(userLocation).title("Your Location"));
                    Toast.makeText(this.getApplicationContext(),lastKnownLocation.getLatitude()+"    "+lastKnownLocation.getLongitude(),Toast.LENGTH_SHORT).show();

                    //mMap.moveCamera(CameraUpdateFactory.newLatLng(userLocation));

                }
            }
        }


    }
}

