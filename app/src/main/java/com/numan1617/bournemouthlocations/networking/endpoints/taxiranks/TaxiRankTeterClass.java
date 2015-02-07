package com.numan1617.bournemouthlocations.networking.endpoints.taxiranks;

import com.numan1617.bournemouthlocations.models.TaxiRanksResponse;
import com.numan1617.bournemouthlocations.networking.ApiClient;

import retrofit.RestAdapter;
import rx.Observer;

/**
 * Created by jamesnewman on 07/02/15.
 */
public class TaxiRankTeterClass {

    public static void main(String[] args) {
        final ApiClient apiClient = new ApiClient(
                "http://bmth-loc-api.vdtapp.com/api/v1",
                "Test",
                "Build",
                "UA",
                RestAdapter.LogLevel.FULL
        );


        final TaxiRankProvider taxiRankProvider = new TaxiRankProviderFromRetrofit(apiClient.getLocationsService());
        taxiRankProvider.getTaxiRanks().subscribe(new Observer<TaxiRanksResponse>() {
            @Override
            public void onCompleted() {
                System.out.println("taxiRankProvider.getTaxiRanks() onCompleted");

            }

            @Override
            public void onError(Throwable e) {
                System.out.println("taxiRankProvider.getTaxiRanks() onError");
                e.printStackTrace();
            }

            @Override
            public void onNext(TaxiRanksResponse taxiRanksResponse) {
                System.out.println("taxiRankProvider.getTaxiRanks() onNext");
            }
        });
    }

}
