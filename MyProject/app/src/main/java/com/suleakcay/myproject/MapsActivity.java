package com.suleakcay.myproject;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
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
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
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
                mMap.animateCamera(CameraUpdateFactory.zoomIn());
            }
        });
        final Button btn_map=(Button)findViewById(R.id.btn_btk);
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMap.getMapType()==GoogleMap.MAP_TYPE_NORMAL){
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                    btn_map.setText("NORMAL");

                }else{
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    btn_map.setText("UYDU GÖRÜNTÜSÜ");
                }
            }
        });
        Button btn_git=(Button)findViewById(R.id.btn_git);
        btn_git.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText text_location=(EditText)findViewById(R.id.text_location);
                String location=text_location.getText().toString();
                if(location!=null &&location!=("")){
                    List<Address>addressList=null;
                    Geocoder geocoder=new Geocoder(MapsActivity.this);
                    try{
                        addressList=geocoder.getFromLocationName(location,1);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                    Address address=addressList.get(0);
                    LatLng latLng=new LatLng(address.getLatitude(),address.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title("Konum:"+location));
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));


                }
            }
        });
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng turkey = new LatLng(36, 42);
        mMap.addMarker(new MarkerOptions().position(turkey).title("Marker in Turkey"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(turkey));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            mMap.setMyLocationEnabled(true);
        }else{
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }
    }
    @Override
    public  void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,@NonNull int[] grantResult){
        if (requestCode==1){
            if(grantResult[0]==PackageManager.PERMISSION_GRANTED){
                if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                    mMap.setMyLocationEnabled(true);
                }
            }else {
                Toast.makeText(getApplicationContext(),"Don't  permissions a location",Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode,permissions,grantResult);
    }

}
