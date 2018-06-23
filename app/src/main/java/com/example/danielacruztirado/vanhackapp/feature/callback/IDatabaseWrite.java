package com.example.danielacruztirado.vanhackapp.feature.callback;

import com.example.danielacruztirado.vanhackapp.data.FavoritePlace;

public interface IDatabaseWrite {

    void onWriteObject(FavoritePlace favoritePlace);
    void onDatabaseError(String message);
}