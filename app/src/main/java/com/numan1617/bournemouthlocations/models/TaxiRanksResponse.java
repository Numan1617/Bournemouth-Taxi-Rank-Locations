package com.numan1617.bournemouthlocations.models;

import com.numan1617.bournemouthlocations.halarious.HalEmbedded;
import com.numan1617.bournemouthlocations.halarious.HalLink;
import com.numan1617.bournemouthlocations.halarious.HalResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jamesnewman on 07/02/15.
 */
public class TaxiRanksResponse implements HalResource {

    @HalLink
    private String self;

    @HalEmbedded(name = "taxiRanks")
    private List<Location> locations = new ArrayList<>();

    public String getSelf() {
        return self;
    }

    public List<Location> getLocations() {
        return locations;
    }
}
