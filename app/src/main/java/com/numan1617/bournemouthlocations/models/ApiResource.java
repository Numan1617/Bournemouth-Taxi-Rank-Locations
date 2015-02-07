package com.numan1617.bournemouthlocations.models;

import com.numan1617.bournemouthlocations.halarious.HalLink;
import com.numan1617.bournemouthlocations.halarious.HalResource;

/**
 * Created by jamesnewman on 07/02/15.
 */
public class ApiResource implements HalResource {

    private String name;

    private String description;

    @HalLink
    private String self;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSelf() {
        return self;
    }
}
