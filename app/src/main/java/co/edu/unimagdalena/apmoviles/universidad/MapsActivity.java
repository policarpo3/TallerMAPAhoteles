package co.edu.unimagdalena.apmoviles.universidad;

import androidx.fragment.app.FragmentActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import co.edu.unimagdalena.apmoviles.universidad.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    HotelController hotelController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        hotelController = new HotelController(this);

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
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View v = getLayoutInflater().inflate(R.layout.custom_infowindow, null);

                v.setLayoutParams(new RelativeLayout.LayoutParams(500, RelativeLayout.LayoutParams.WRAP_CONTENT));

                TextView description = (TextView) v.findViewById(R.id.marker_description);
                TextView name = (TextView) v.findViewById(R.id.marker_title);

                name.setText(marker.getTitle());
                description.setText(marker.getSnippet());

                return v;
            }
        });

        Cursor cursor = hotelController.allHoteles2();
        try {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                LatLng pos = new LatLng(Double.parseDouble(cursor.getString(6)), Float.parseFloat(cursor.getString(7)));
                mMap.addMarker(new MarkerOptions()
                        .position(pos)
                        .title(cursor.getString(1))
                        .snippet("\nDepartamento: "+cursor.getString(2)+"\nCiudad: "+cursor.getString(3)+"\nEstrellas: "+cursor.getString(4)+"\nDirección: "+cursor.getString(5))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_hotel)));
            }

        } finally {
            cursor.close();
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(11.234276, -74.194580), 13 ));

    }


}