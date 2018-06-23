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

import java.util.UUID;

public class SearchPlacesActivity extends BaseFragmentActivity implements PlaceSelectionListener {

    private String TAG = SearchPlacesActivity.class.getName();
    private PlaceAutocompleteFragment autocompleteFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //writePlaceOnDatabase();
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
    }

    private void writePlaceOnDatabase(){
        FavoritePlace place = new FavoritePlace();
        place.setId(UUID.randomUUID().toString());
        place.setName("my vanhack name");
        place.setAddress("my vanhack address");
        place.setPhone("my vanhack phone");
        place.setWeb("my vanhack web");

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

    @Override
    public void onPlaceSelected(Place place) {
        Log.i(TAG, "Place: " + place.getName());
    }

    @Override
    public void onError(Status status) {
        Log.i(TAG, "An error occurred: " + status);
    }
}