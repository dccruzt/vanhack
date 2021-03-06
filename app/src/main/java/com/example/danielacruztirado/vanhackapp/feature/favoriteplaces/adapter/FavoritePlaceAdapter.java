package com.example.danielacruztirado.vanhackapp.feature.favoriteplaces.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.danielacruztirado.vanhackapp.R;
import com.example.danielacruztirado.vanhackapp.favoriteplaces.data.FavoritePlace;

import java.util.List;

public class FavoritePlaceAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<FavoritePlace> places;

    public FavoritePlaceAdapter(List<FavoritePlace> places){
        this.places = places;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place, parent, false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        FavoritePlace place = places.get(position);

        PlaceViewHolder placeViewHolder = (PlaceViewHolder) holder;

        if(place != null){
            placeViewHolder.nameText.setText("Name: " + place.getName());
            placeViewHolder.addressText.setText("Address: " + place.getAddress());
            placeViewHolder.phoneText.setText("Phone: " + place.getPhone());
            placeViewHolder.webText.setText("Web: " + place.getWeb());
        }
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public void updateAdapter(List<FavoritePlace> myPlaces) {
        this.places = myPlaces;
        notifyDataSetChanged();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder{
        TextView nameText;
        TextView addressText;
        TextView phoneText;
        TextView webText;

        public PlaceViewHolder(View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.text_place_name);
            addressText = itemView.findViewById(R.id.text_place_address);
            phoneText = itemView.findViewById(R.id.text_place_phone);
            webText = itemView.findViewById(R.id.text_place_web);
        }
    }
}