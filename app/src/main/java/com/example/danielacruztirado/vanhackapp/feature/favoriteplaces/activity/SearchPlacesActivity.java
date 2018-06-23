package com.example.danielacruztirado.vanhackapp.feature.favoriteplaces.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.danielacruztirado.vanhackapp.R;
import com.example.danielacruztirado.vanhackapp.favoriteplaces.data.FavoritePlace;
import com.example.danielacruztirado.vanhackapp.favoriteplaces.feature.callback.IDatabaseWrite;
import com.example.danielacruztirado.vanhackapp.feature.base.BaseFragmentActivity;
import com.example.danielacruztirado.vanhackapp.init.VanhackDatabase;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.UUID;

public class SearchPlacesActivity extends BaseFragmentActivity implements PlaceSelectionListener, OnMapReadyCallback {

    private String TAG = SearchPlacesActivity.class.getName();

    private PlaceAutocompleteFragment autocompleteFragment;
    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*VanhackPlacesAPI.getInstance(this).init(this, new IConnectionListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    @Override
    public int getLayout() {
        return R.layout.activity_search_places;
    }

    @Override
    public void initViews() {

        autocompleteFragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.fragment_places_autocomplete);
        if(autocompleteFragment != null){
            autocompleteFragment.setOnPlaceSelectedListener(this);
        }

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if(mapFragment != null){
            mapFragment.getMapAsync(this);
        }
    }

    // FIREBASE DATABASE

    private void writePlaceOnDatabase(FavoritePlace place){

        VanhackDatabase.getInstance(this).queryToWrite("places", place, new IDatabaseWrite() {
            @Override
            public void onWriteObject(FavoritePlace favoritePlace) {
            }

            @Override
            public void onDatabaseError(String message) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // GOOGLE MAPS

    @Override
    public void onPlaceSelected(Place place) {
        Log.i(TAG, "Place: " + place.getName());

        LatLng latLng = place.getLatLng();
        googleMap.addMarker(new MarkerOptions().position(latLng).title(place.getName().toString()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        //Create a place with the api information
        FavoritePlace favoritePlace = new FavoritePlace();
        favoritePlace.setId(UUID.randomUUID().toString());
        favoritePlace.setName(place.getName().toString());
        favoritePlace.setAddress(place.getAddress().toString());
        favoritePlace.setPhone(place.getPhoneNumber().toString());
        favoritePlace.setWeb(place.getWebsiteUri().toString());

        //Save on firebase database
        writePlaceOnDatabase(favoritePlace);
    }

    @Override
    public void onError(Status status) {
        Log.i(TAG, "An error occurred: " + status);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        // Showing Sydney location
        LatLng sydney = new LatLng(-34, 151);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}