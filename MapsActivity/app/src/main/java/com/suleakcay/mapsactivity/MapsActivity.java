package com.suleakcay.mapsactivity;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback , GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;  //Haritamiz
    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
         locationManager=(LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
         locationListener=new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {  //kullanıcının yeri degistiginde ne olacak vs
              /*  mMap.clear();
               LatLng userLocation=new LatLng(location.getLatitude(),location.getLongitude());
               mMap.addMarker(new MarkerOptions().position(userLocation).title("Your Location"));
               mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,15));*/


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
      if(Build.VERSION.SDK_INT>=23){
          if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
              requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
          }else{
              locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
              Location lastlocation=  locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
              System.out.println("last location:"+ lastlocation);
              LatLng userLastLocation=new LatLng(lastlocation.getLatitude(),lastlocation.getLongitude());
              mMap.addMarker(new MarkerOptions().title("Your Location").position(userLastLocation));
              mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLastLocation,15));

          }

      }

      else {
          locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
          Location lastlocation=  locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
          System.out.println("last location:"+ lastlocation);
          LatLng userLastLocation=new LatLng(lastlocation.getLatitude(),lastlocation.getLongitude());
          mMap.addMarker(new MarkerOptions().title("Your Location").position(userLastLocation));
          mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLastLocation,15));
      }
        mMap.setOnMapLongClickListener(this);

        }

        // Add a marker in Sydney and move the camera



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {//kullanıcı izin verdiginde ne olacak
        if(grantResults.length>0){
            if(requestCode==1){
                if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        mMap.clear();
        Geocoder geocoder=new Geocoder(getApplicationContext(), Locale.getDefault());
        String address= "";
      try{
          List<Address> addressList=geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);
          if(addressList!=null && addressList.size()>0){
              if(addressList.get(0).getThoroughfare()!=null){
                  address+=addressList.get(0).getThoroughfare();


                  if(addressList.get(0).getSubThoroughfare()!=null){
                      address+=addressList.get(0).getSubThoroughfare();
                  }
              }
          }
      }catch (IOException e){
          e.printStackTrace();
      }
      if(address.matches("")){
          address="No Address";

      }
      mMap.addMarker(new MarkerOptions().position(latLng).title(address));

    }
}
