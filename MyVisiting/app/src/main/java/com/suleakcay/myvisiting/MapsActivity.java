package com.suleakcay.myvisiting;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;
    static SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @SuppressLint("ServiceCast")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLongClickListener((GoogleMap.OnMapLongClickListener) this);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                SharedPreferences sharedPreferences=MapsActivity.this.getSharedPreferences("com.suleakcay.myvisiting",MODE_PRIVATE);
                boolean firstTimeCheck=sharedPreferences.getBoolean("notFirstTime",false);
                if(!firstTimeCheck){
                    LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
                    sharedPreferences.edit().putBoolean("notFirstTime",true).apply();
                }


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

        //her yerde kullanılır
        if (Build.VERSION.SDK_INT >= 23) {
           if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
               requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);

            }else {
               locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
               mMap.clear();
               Location lastlocation=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
               LatLng latlng=new LatLng(lastlocation.getLatitude(),lastlocation.getLongitude());
               mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,15));
           }

        }else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
            Location lastlocation=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
           if(lastlocation!=null){
               LatLng latlng=new LatLng(lastlocation.getLatitude(),lastlocation.getLongitude());
               mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,15));
           }

        }

    }
//kullanıcı izni yoksa bu izni  verdiyse ne olacak
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0) {
            if (requestCode == 1) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                }
            }
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {
        Geocoder geocoder=new Geocoder(getApplicationContext(), Locale.getDefault());
        String adress="";
        try {
            List<Address> addressList=geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);
            if(addressList!=null&&addressList.size()>0){
                if(addressList.get(0).getThoroughfare()!=null){
                    adress+=addressList.get(0).getThoroughfare();
                    if (addressList.get(0).getThoroughfare()!=null){
                        adress+=addressList.get(0).getSubThoroughfare();
                    }
                }
            }else{
                adress ="New Place";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
            mMap.addMarker(new MarkerOptions().title(adress).position(latLng));
           Toast.makeText(getApplicationContext(),"New Place OK!",Toast.LENGTH_LONG).show();
           try{
               Double l1=latLng.latitude;
               Double l2=latLng.longitude;    //burada enlem ve boylamı aldık
               String coord1=l1.toString();
               String coord2=l2.toString();
               database=this.openOrCreateDatabase("Places",MODE_PRIVATE,null);
               database.execSQL("CREATE TABLE IF NOT EXISTS places(name VARCHAR,latitude VARCHAR,longitude VARCHAR)");
               String toCompiler="INSERT INTO places (name,latitude,longitude) VALUES (?,?,?)";
               SQLiteStatement sqLiteStatement=database.compileStatement(toCompiler);
               sqLiteStatement.bindString(1,adress);
               sqLiteStatement.bindString(2,coord1);
               sqLiteStatement.bindString(3,coord2);
               sqLiteStatement.execute();



           }catch (Exception ex){

           }
    }
}
