package com.numan1617.bournemouthlocations.networking.endpoints.taxiranks;

import com.numan1617.bournemouthlocations.models.Location;
import com.numan1617.bournemouthlocations.models.TaxiRanksResponse;

import rx.Observable;

/**
 * Created by jamesnewman on 07/02/15.
 */
public interface TaxiRankProvider {

    Observable<TaxiRanksResponse> getTaxiRanks();

    Observable<Location> getTaxiRank(long taxiRankId);
}
