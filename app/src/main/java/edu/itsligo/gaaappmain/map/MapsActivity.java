package edu.itsligo.gaaappmain.map;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import edu.itsligo.gaaappmain.R;

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

        // Add a marker in Sydney and move the camera
        LatLng john = new LatLng(54.23775533895486, -8.463441846027369);
        mMap.addMarker(new MarkerOptions().position(john).title("St Johns GAA Club"));
        float zoomLevel = (float) 10.0;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(john, zoomLevel));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(john));


        LatLng scarden = new LatLng(54.29248811577066, -8.546229059012857);
        mMap.addMarker(new MarkerOptions().position(scarden).title("Sligo GAA Centre of Excellence Scarden"));

        LatLng Molaise = new LatLng(54.41254041736245, -8.520136530799151);
        mMap.addMarker(new MarkerOptions().position(Molaise).title("St Molaise Gaels GAA Club"));

        LatLng Coolera = new LatLng(54.26282126711893, -8.535242731343928);
        mMap.addMarker(new MarkerOptions().position(Coolera).title("Coolera Strandhill GAA Club"));

        LatLng Markievicz = new LatLng(54.28126533362085, -8.46932476533036);
        mMap.addMarker(new MarkerOptions().position(Markievicz).title("Markievicz Park GAA Stadium"));

        LatLng OwenMore = new LatLng(54.19217643195802, -8.48580425900009);
        mMap.addMarker(new MarkerOptions().position(OwenMore).title("Owenmore Gaels GAA Club"));

        LatLng Coolaney = new LatLng(54.10933623254828, -8.661585505351514);
        mMap.addMarker(new MarkerOptions().position(Coolaney).title("Coolaney Mullinabreena GAA Club"));

        LatLng tubberCurry = new LatLng(54.07389763972696, -8.717890434654771);
        mMap.addMarker(new MarkerOptions().position(tubberCurry).title("Tubbercurry GAA Club"));

        LatLng Tourlestrane = new LatLng(54.056166990059296, -8.826380420385439);
        mMap.addMarker(new MarkerOptions().position(Tourlestrane).title("Tourlestrane GAA club"));
    }
}