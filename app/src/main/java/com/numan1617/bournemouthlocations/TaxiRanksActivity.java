package com.numan1617.bournemouthlocations;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.numan1617.bournemouthlocations.models.Location;
import com.numan1617.bournemouthlocations.models.TaxiRanksResponse;
import com.numan1617.bournemouthlocations.networking.endpoints.taxiranks.TaxiRankProvider;

import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.ContentView;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

@ContentView(R.layout.activity_taxi_ranks)
public class TaxiRanksActivity extends RoboFragmentActivity {

    @SuppressWarnings("unused")
    private static final String TAG = BaseApplication.class.getSimpleName();

    @Inject
    private TaxiRankProvider mTaxiRankProvider;

    @Inject
    private CompositeSubscription mCompositeSubscription;

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate called");

        Observable<TaxiRanksResponse> taxiRanksResponseObservable = mTaxiRankProvider.getTaxiRanks()
                .observeOn(AndroidSchedulers.mainThread());

        mCompositeSubscription
                .add(taxiRanksResponseObservable.subscribe(new Observer<TaxiRanksResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "taxiRanksResponseObservable onError");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(TaxiRanksResponse taxiRanksResponse) {
                        Log.e(TAG, "taxiRanksResponseObservable onNext");
                        Log.e(TAG, "taxiRanksResponse self "+ taxiRanksResponse.getSelf());
                        for (Location taxiRank : taxiRanksResponse.getLocations()) {
                            mMap.addMarker(
                                    new MarkerOptions()
                                            .position(
                                                    new LatLng(
                                                            taxiRank.getLat(),
                                                            taxiRank.getLng()
                                                    )
                                            )
                                            .title(taxiRank.getName()));
                        }
                    }
                }));
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeSubscription.unsubscribe();
    }
}
