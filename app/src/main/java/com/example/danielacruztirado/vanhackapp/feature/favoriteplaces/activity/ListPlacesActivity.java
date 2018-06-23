package com.example.danielacruztirado.vanhackapp.feature.favoriteplaces.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danielacruztirado.vanhackapp.R;
import com.example.danielacruztirado.vanhackapp.favoriteplaces.feature.callback.IDatabaseRead;
import com.example.danielacruztirado.vanhackapp.favoriteplaces.data.FavoritePlace;
import com.example.danielacruztirado.vanhackapp.feature.base.BaseActivity;
import com.example.danielacruztirado.vanhackapp.feature.favoriteplaces.adapter.FavoritePlaceAdapter;
import com.example.danielacruztirado.vanhackapp.init.VanhackDatabase;

import java.util.ArrayList;
import java.util.List;

public class ListPlacesActivity extends BaseActivity {

    private String TAG = SearchPlacesActivity.class.getName();

    private Button addPlaceButton;
    private RecyclerView placesList;
    private TextView emptyStateText;
    private List<FavoritePlace> places;
    private FavoritePlaceAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        readPlacesOnDatabase();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_list_places;
    }

    @Override
    public void initViews() {
        addPlaceButton = (Button) findViewById(R.id.button_add_places);
        placesList = (RecyclerView) findViewById(R.id.list_places);
        emptyStateText = (TextView) findViewById(R.id.text_empty_state);

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

        if(emptyStateText != null){
            emptyStateText.setVisibility(View.GONE);
        }
    }

    private void openMap(){

        Intent intent = new Intent(this, SearchPlacesActivity.class);
        startActivity(intent);
    }

    private void getLayoutState(){

        if(places == null || places.size() == 0){
            if(emptyStateText != null){
                emptyStateText.setVisibility(View.VISIBLE);
                placesList.setVisibility(View.GONE);
            }
        }else {
            if(emptyStateText != null){
                adapter.updateAdapter(places);
                emptyStateText.setVisibility(View.GONE);
                placesList.setVisibility(View.VISIBLE);
            }
        }
    }

    private void readPlacesOnDatabase(){

        VanhackDatabase.getInstance(this).queryToRead("places", new IDatabaseRead() {

            @Override
            public void onReadObject(List<FavoritePlace> favoritePlaces) {
                Log.i(TAG, String.valueOf(favoritePlaces.size()));

                places = favoritePlaces;
                getLayoutState();
            }

            @Override
            public void onDatabaseError(String message) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}