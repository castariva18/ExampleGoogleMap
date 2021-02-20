package com.suncode.examplegooglemap;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btn_get_location);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        //get lokasi dari database
        LatLng bandung = new LatLng(-6.917731498045556, 107.6156894322087);
        float zoomLevel = 15f;
        map.addMarker(new MarkerOptions().position(bandung).title("Bandung city"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(bandung, zoomLevel));
        setMapLongClick(map);
    }

    private void setMapLongClick(GoogleMap map) {
        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                map.addMarker(new MarkerOptions().position(latLng));

                Log.d("LONG", "onMapLongClick: " + latLng.latitude + " - " + latLng.longitude );

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //kirim data lokasi ke database
                        Toast.makeText(MainActivity.this,"Get:" + latLng ,Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
    
}