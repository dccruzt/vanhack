package com.example.danielacruztirado.vanhackapp.init;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.danielacruztirado.vanhackapp.callback.IDatabaseAction;
import com.example.danielacruztirado.vanhackapp.data.FavoritePlace;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VanhackDatabase {

    private static VanhackDatabase instance;
    private static FirebaseDatabase database;

    public static VanhackDatabase getInstance(){
        if(instance != null){
            instance = new VanhackDatabase();
            database = FirebaseDatabase.getInstance();
            return instance;
        }
        return instance;
    }

    public void queryToRead(String reference, final IDatabaseAction callback){

        DatabaseReference referenceDB = database.getReference(reference);
        referenceDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                FavoritePlace favoritePlace = dataSnapshot.getValue(FavoritePlace.class);
                callback.onReadObject(favoritePlace);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void queryToWrite(String reference, final FavoritePlace favoritePlace, final IDatabaseAction callback){
        DatabaseReference referenceDB = database.getReference(reference);
        referenceDB.child(favoritePlace.getId()).setValue(favoritePlace, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                callback.onWriteObject(favoritePlace);
            }
        });
    }
}