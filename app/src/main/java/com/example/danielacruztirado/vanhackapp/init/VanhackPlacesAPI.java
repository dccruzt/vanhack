package com.example.danielacruztirado.vanhackapp.init;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.example.danielacruztirado.vanhackapp.feature.favoriteplaces.callback.IConnectionListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;

public class VanhackPlacesAPI {

    private static VanhackPlacesAPI instance;
    private GoogleApiClient mGoogleApiClient;
    private static Context mContext;

    public static VanhackPlacesAPI getInstance(Context context){
        if(instance == null){
            mContext = context;
            instance = new VanhackPlacesAPI();
            return instance;
        }
        return instance;
    }

    public void init(FragmentActivity fragmentActivity, final IConnectionListener callback){

        mGoogleApiClient = new GoogleApiClient
                .Builder(mContext)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(fragmentActivity, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        if(callback != null){
                            callback.onError(connectionResult.getErrorMessage());
                        }
                    }
                })
                .build();
    }
}