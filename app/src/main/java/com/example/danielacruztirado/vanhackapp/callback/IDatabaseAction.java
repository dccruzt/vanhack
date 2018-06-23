package com.example.danielacruztirado.vanhackapp.callback;

import com.example.danielacruztirado.vanhackapp.data.FavoritePlace;

public interface IDatabaseAction {

    void onWriteObject(FavoritePlace favoritePlace);
    void onReadObject(FavoritePlace favoritePlace);
}