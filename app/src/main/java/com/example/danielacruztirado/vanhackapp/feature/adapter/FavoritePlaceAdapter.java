package com.example.danielacruztirado.vanhackapp.feature.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.danielacruztirado.vanhackapp.R;
import com.example.danielacruztirado.vanhackapp.data.FavoritePlace;

import java.util.List;

public class FavoritePlaceAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context context;
    private List<FavoritePlace> places;

    public FavoritePlaceAdapter(Context context, List<FavoritePlace> places){
        this.context = context;
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
            placeViewHolder.nameText.setText(place.getName());
            placeViewHolder.addressText.setText(place.getAddress());
            placeViewHolder.phoneText.setText(place.getPhone());
            placeViewHolder.webText.setText(place.getWeb());
        }
    }

    @Override
    public int getItemCount() {
        return 0;
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