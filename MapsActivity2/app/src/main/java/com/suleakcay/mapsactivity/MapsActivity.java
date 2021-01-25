package com.suleakcay.mapsactivity;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.DownloadManager;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ZoomControls zoom=(ZoomControls)findViewById(R.id.zoom);
        zoom.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.animateCamera(CameraUpdateFactory.zoomOut());
            }
        });
        zoom.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.animateCamera(CameraUpdateFactory.zoomOut());
            }
        });
        final Button btn_MapType=(Button) findViewById(R.id.btn_Sat);
        btn_MapType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMap.getMapType()==GoogleMap.MAP_TYPE_NORMAL){
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                    btn_MapType.setText("NORMAL");
                }else{
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    btn_MapType.setText("UYDU");
                }
            }
        });
        Button btnGo=(Button) findViewById(R.id.btn_Go);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etLocation=(EditText)findViewById(R.id.et_location);
                String location=etLocation.getText().toString();
                if(location!=null&&!location.equals("")){
                    List<Address>addressList=null;
                    //geocoder verilen enlem ve boylamdaki adresi bir array olarak döndürmektedir
                    Geocoder geocoder=new Geocoder(MapsActivity.this);
                    try{
                        addressList=geocoder.getFromLocationName(location,1);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                    Address address=addressList.get(0);
                    LatLng latLng=new LatLng(address.getLatitude(),address.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title("Location"+location));
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                }
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            mMap.setMyLocationEnabled(true);
        }else{
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==1){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {
                    mMap.setMyLocationEnabled(true);
                }
            }else{
                Toast.makeText(getApplicationContext(),"Kullanıcı konum iznini vermedi", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
