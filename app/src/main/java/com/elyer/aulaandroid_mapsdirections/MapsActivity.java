package com.elyer.aulaandroid_mapsdirections;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

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

        LatLng cruzeiro = new LatLng(-7.243120, -35.903414); //Adiciona um marcador em Campina Grande
        mMap.addMarker(new MarkerOptions()
                .position(cruzeiro)
                .title("Marker em CAmpina Grande")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.pushpin)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cruzeiro, 18)); //Mover a camera para a localização fornecida
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); //Alterar o tipo de mapa

        LatLng inicio = new LatLng(-7.245026, -35.905496);
        LatLng intermediario01 = new LatLng(-7.245367, -35.906183);
        LatLng intermediario02 = new LatLng(-7.248060, -35.905475);
        LatLng intermediario03 = new LatLng(-7.248177, -35.906977);
        LatLng fim = new LatLng(-7.248752, -35.906998);

        mMap.addPolyline(new PolylineOptions()
                .add(inicio, intermediario01, intermediario02, intermediario03, fim)
                .width(10)
                .color(Color.RED)); //Adiciona uma linha entre as coordenadas passadas

        //Pegando a permissão para acessar a localização
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);//Exibindo o botão de ir para a propria localização

        // https://developers.google.com/maps/documentation/directions/intro?hl=pt-br
    }
}
