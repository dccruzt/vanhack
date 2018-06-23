package com.example.danielacruztirado.vanhackapp.favoriteplaces.feature.callback;

import com.example.danielacruztirado.vanhackapp.favoriteplaces.data.FavoritePlace;

public interface IDatabaseWrite {

    void onWriteObject(FavoritePlace favoritePlace);
    void onDatabaseError(String message);
}