package com.numan1617.bournemouthlocations.models;

import com.numan1617.bournemouthlocations.halarious.HalResource;

/**
 * Created by jamesnewman on 07/02/15.
 */
public class Location implements HalResource {

    private long id;

    private String name;

    private double lat;

    private double lng;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
