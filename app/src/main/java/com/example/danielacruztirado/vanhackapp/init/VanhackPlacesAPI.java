package com.example.danielacruztirado.vanhackapp.init;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.example.danielacruztirado.vanhackapp.feature.favoriteplaces.callback.IConnectionListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;

public class VanhackPlacesAPI {

    private static VanhackPlacesAPI instance;
    private GoogleApiClient mGoogleApiClient;
    private static Context mContext;

    private GeoDataClient mGeoDataClient;
    private PlaceDetectionClient mPlaceDetectionClient;
    private String mFusedLocationProviderClient;

    public static VanhackPlacesAPI getInstance(Context context){
        if(instance == null){
            mContext = context;
            instance = new VanhackPlacesAPI();
            return instance;
        }
        return instance;
    }

    public void init(FragmentActivity fragmentActivity, final IConnectionListener callback){

        /*mGeoDataClient = Places.getGeoDataClient(mContext);
        mPlaceDetectionClient = Places.getPlaceDetectionClient(mContext);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);*/

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