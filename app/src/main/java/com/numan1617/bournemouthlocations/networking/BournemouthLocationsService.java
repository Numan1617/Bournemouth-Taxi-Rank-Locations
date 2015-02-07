package com.numan1617.bournemouthlocations.networking;

import com.numan1617.bournemouthlocations.halarious.HalResource;
import com.numan1617.bournemouthlocations.models.ApiResourceResponse;
import com.numan1617.bournemouthlocations.models.Location;
import com.numan1617.bournemouthlocations.models.TaxiRanksResponse;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by jamesnewman on 07/02/15.
 */
public interface BournemouthLocationsService {

    @GET("/")
    Observable<HalResource<ApiResourceResponse>> getApiResources();

    @GET("/taxi-ranks")
    Observable<HalResource<TaxiRanksResponse>> getTaxiRanks();

    @GET("/taxi-ranks?{rankId}")
    Observable<HalResource<Location>> getTaxiRanks(
            @Path("rankId") long rankId
    );
}
