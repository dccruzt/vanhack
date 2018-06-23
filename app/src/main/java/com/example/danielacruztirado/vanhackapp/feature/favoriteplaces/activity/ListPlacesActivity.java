package com.example.danielacruztirado.vanhackapp.feature.favoriteplaces.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.danielacruztirado.vanhackapp.R;
import com.example.danielacruztirado.vanhackapp.favoriteplaces.feature.callback.IDatabaseRead;
import com.example.danielacruztirado.vanhackapp.favoriteplaces.data.FavoritePlace;
import com.example.danielacruztirado.vanhackapp.favoriteplaces.feature.adapter.FavoritePlaceAdapter;
import com.example.danielacruztirado.vanhackapp.favoriteplaces.feature.callback.IDatabaseWrite;
import com.example.danielacruztirado.vanhackapp.feature.base.BaseActivity;
import com.example.danielacruztirado.vanhackapp.init.VanhackDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ListPlacesActivity extends BaseActivity {

    private Button addPlaceButton;
    private RecyclerView placesList;
    private List<FavoritePlace> places;
    private FavoritePlaceAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        readPlacesOnDatabase();
        //writePlaceOnDatabase();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_list_places;
    }

    @Override
    public void initViews() {
        addPlaceButton = (Button) findViewById(R.id.button_add_places);
        placesList = (RecyclerView) findViewById(R.id.list_places);

        places = new ArrayList<>();
        adapter = new FavoritePlaceAdapter(places);

        if(placesList != null){
            placesList.setLayoutManager(new LinearLayoutManager(this));
            placesList.setAdapter(adapter);
        }

        if(addPlaceButton != null){
            addPlaceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openMap();
                }
            });
        }
    }

    private void openMap(){

        Intent intent = new Intent(this, SearchPlacesActivity.class);
        startActivity(intent);
    }

    private void readPlacesOnDatabase(){

        VanhackDatabase.getInstance(this).queryToRead("places", new IDatabaseRead() {

            @Override
            public void onReadObject(List<FavoritePlace> favoritePlaces) {
                Log.i("danielaTest", String.valueOf(favoritePlaces.size()));
                places = favoritePlaces;
                adapter.updateAdapter(places);
            }

            @Override
            public void onDatabaseError(String message) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
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
}