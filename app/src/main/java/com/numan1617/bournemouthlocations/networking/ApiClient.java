package com.numan1617.bournemouthlocations.networking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import com.numan1617.bournemouthlocations.halarious.HalDeserializer;
import com.numan1617.bournemouthlocations.halarious.HalExclusionStrategy;
import com.numan1617.bournemouthlocations.halarious.HalResource;
import com.numan1617.bournemouthlocations.halarious.HalSerializer;
import com.numan1617.bournemouthlocations.models.ApiResource;
import com.numan1617.bournemouthlocations.models.ApiResourceResponse;
import com.numan1617.bournemouthlocations.models.Location;
import com.numan1617.bournemouthlocations.models.TaxiRanksResponse;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;

/**
 * Created by jamesnewman on 07/02/15.
 */
public class ApiClient {

    private final BournemouthLocationsService mLocationsService;

    private final RestAdapter mRestAdapter;

    public ApiClient(String baseUrl, String appId, final String appVersion, final String userAgent) {
        this(baseUrl, appId, appVersion, userAgent, RestAdapter.LogLevel.NONE);
    }

    public ApiClient(String baseUrl, final String appId, final String appVersion, final String userAgent, RestAdapter.LogLevel logLevel) {


        // set up the request interceptor to add headers to all requests
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("User-Agent", userAgent + "; " + appId + "; " + appVersion);
            }
        };

        // Build the Retrofit REST adaptor pointing to the URL specified
        mRestAdapter = new RestAdapter.Builder()
                .setLogLevel(logLevel)
                .setRequestInterceptor(requestInterceptor)
                .setConverter(getConverter())
                .setEndpoint(baseUrl)
                .build();


        mLocationsService = mRestAdapter.create(BournemouthLocationsService.class);
    }

    public RestAdapter getRestAdapter() {
        return mRestAdapter;
    }

    public Converter getConverter() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(HalResource.class, new HalSerializer());

        builder.registerTypeAdapter(new TypeToken<HalResource<Location>>() {
        }.getType(), new HalDeserializer(Location.class));
        builder.registerTypeAdapter(new TypeToken<HalResource<ApiResource>>() {
        }.getType(), new HalDeserializer(ApiResource.class));
        builder.registerTypeAdapter(new TypeToken<HalResource<ApiResourceResponse>>() {
        }.getType(), new HalDeserializer(ApiResourceResponse.class));
        builder.registerTypeAdapter(new TypeToken<HalResource<TaxiRanksResponse>>() {
        }.getType(), new HalDeserializer(TaxiRanksResponse.class));

        builder.setExclusionStrategies(new HalExclusionStrategy());
        Gson gson = builder.create();

        return new GsonConverter(gson);
    }

    public BournemouthLocationsService getLocationsService() {
        return mLocationsService;
    }
}
