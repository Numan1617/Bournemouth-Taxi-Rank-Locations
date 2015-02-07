package com.numan1617.bournemouthlocations.models;

import com.numan1617.bournemouthlocations.halarious.HalEmbedded;
import com.numan1617.bournemouthlocations.halarious.HalResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jamesnewman on 07/02/15.
 */
public class ApiResourceResponse implements HalResource {

    @HalEmbedded
    private List<ApiResource> mResources = new ArrayList<>();
}
