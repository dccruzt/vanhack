package com.example.danielacruztirado.vanhackapp.favoriteplaces.feature.callback;

import com.example.danielacruztirado.vanhackapp.favoriteplaces.data.FavoritePlace;

import java.util.List;

public interface IDatabaseRead {

    void onReadObject(List<FavoritePlace> favoritePlaces);
    void onDatabaseError(String message);
}