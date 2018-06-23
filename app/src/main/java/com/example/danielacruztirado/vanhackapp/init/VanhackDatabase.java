package com.example.danielacruztirado.vanhackapp.init;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.danielacruztirado.vanhackapp.feature.callback.IDatabaseRead;
import com.example.danielacruztirado.vanhackapp.data.FavoritePlace;
import com.example.danielacruztirado.vanhackapp.feature.callback.IDatabaseWrite;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VanhackDatabase {

    private static VanhackDatabase instance;
    private static FirebaseDatabase database;

    public static VanhackDatabase getInstance(Context context){
        if(instance == null){
            FirebaseApp.initializeApp(context);
            instance = new VanhackDatabase();
            database = FirebaseDatabase.getInstance();
            return instance;
        }
        return instance;
    }

    public void queryToRead(String reference, final IDatabaseRead callback){

        DatabaseReference referenceDB = database.getReference(reference);
        referenceDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                GenericTypeIndicator<HashMap<String, FavoritePlace>> objectsGTypeInd = new GenericTypeIndicator<HashMap<String, FavoritePlace>>() {};
                Map<String, FavoritePlace> objectHashMap = dataSnapshot.getValue(objectsGTypeInd);
                ArrayList<FavoritePlace> objectArrayList = new ArrayList<FavoritePlace>(objectHashMap.values());

                callback.onReadObject(objectArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onDatabaseError(databaseError.getMessage());
            }
        });
    }

    public void queryToWrite(String reference, final FavoritePlace favoritePlace, final IDatabaseWrite callback){
        DatabaseReference referenceDB = database.getReference(reference);
        referenceDB.child(favoritePlace.getId()).setValue(favoritePlace, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                callback.onWriteObject(favoritePlace);
            }
        });
    }
}