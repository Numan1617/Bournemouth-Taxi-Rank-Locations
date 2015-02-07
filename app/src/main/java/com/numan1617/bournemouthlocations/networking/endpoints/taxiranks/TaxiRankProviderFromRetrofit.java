package com.numan1617.bournemouthlocations.networking.endpoints.taxiranks;

import com.numan1617.bournemouthlocations.halarious.HalResource;
import com.numan1617.bournemouthlocations.models.Location;
import com.numan1617.bournemouthlocations.models.TaxiRanksResponse;
import com.numan1617.bournemouthlocations.networking.BournemouthLocationsService;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by jamesnewman on 07/02/15.
 */
public class TaxiRankProviderFromRetrofit implements TaxiRankProvider {

    private BournemouthLocationsService mService;

    private Observable<TaxiRanksResponse> mGetTaxiRanksObservable;

    private Observable<Location> mGetTaxiRankObservable;

    public TaxiRankProviderFromRetrofit(BournemouthLocationsService service) {
        mService = service;
    }

    @Override
    public Observable<TaxiRanksResponse> getTaxiRanks() {
        if (mGetTaxiRanksObservable == null) {
            mGetTaxiRanksObservable = mService.getTaxiRanks().flatMap(
                    new Func1<HalResource<TaxiRanksResponse>, Observable<TaxiRanksResponse>>() {
                        @Override
                        public Observable<TaxiRanksResponse> call(
                                final HalResource<TaxiRanksResponse> taxiRanksResponseHalResource) {
                            return Observable
                                    .just((TaxiRanksResponse) taxiRanksResponseHalResource);
                        }
                    });
        }

        return mGetTaxiRanksObservable;
    }

    public void clearmGetTaxiRanksObservable() {
        mGetTaxiRanksObservable = null;
    }

    @Override
    public Observable<Location> getTaxiRank(long taxiRankId) {
        if (mGetTaxiRankObservable == null) {
            mGetTaxiRankObservable = mService.getTaxiRanks(taxiRankId)
                    .flatMap(new Func1<HalResource<Location>, Observable<Location>>() {
                        @Override
                        public Observable<Location> call(
                                HalResource<Location> locationHalResource) {
                            return Observable.just((Location) locationHalResource);
                        }
                    });
        }

        return mGetTaxiRankObservable;
    }

    public void clearmGetTaxiRankObservable() {
        mGetTaxiRankObservable = null;
    }
}
