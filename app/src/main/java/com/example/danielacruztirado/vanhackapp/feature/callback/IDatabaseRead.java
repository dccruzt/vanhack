package com.example.danielacruztirado.vanhackapp.feature.callback;

import com.example.danielacruztirado.vanhackapp.data.FavoritePlace;

import java.util.List;

public interface IDatabaseRead {

    void onReadObject(List<FavoritePlace> favoritePlaces);
    void onDatabaseError(String message);
}