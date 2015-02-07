package com.numan1617.bournemouthlocations;

import com.google.inject.Binder;
import com.google.inject.Module;

import com.numan1617.bournemouthlocations.networking.ApiClient;
import com.numan1617.bournemouthlocations.networking.endpoints.taxiranks.TaxiRankProvider;
import com.numan1617.bournemouthlocations.networking.endpoints.taxiranks.TaxiRankProviderFromRetrofit;
import com.numan1617.bournemouthlocations.utils.UserAgentHelper;

import android.app.Application;
import android.support.annotation.NonNull;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import roboguice.RoboGuice;

/**
 * Created by jamesnewman on 07/02/15.
 */
public class BaseApplication extends Application {

    @SuppressWarnings("unused")
    private static final String TAG = BaseApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        RoboGuice.setUseAnnotationDatabases(false);

        final String ua = UserAgentHelper.getDefaultUserAgentString(getApplicationContext());
        final String buildName = BuildConfig.VERSION_NAME;

        final RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(@NonNull RequestFacade request) {

                request.addHeader(
                        "User-Agent",
                        ua + "; " + getString(R.string.generic_user_agent_text) + "; " + buildName
                );
            }
        };

        final ApiClient apiClient = new ApiClient(
                "http://bmth-loc-api.vdtapp.com/api/v1",
                getString(R.string.generic_user_agent_text),
                buildName,
                ua,
                RestAdapter.LogLevel.FULL
        );

        RoboGuice.overrideApplicationInjector(
                this,
                RoboGuice.newDefaultRoboModule(this), new Module() {
                    @Override
                    public void configure(@NonNull Binder binder) {
                        binder.bind(ApiClient.class).toInstance(apiClient);
                        binder.bind(TaxiRankProvider.class).toInstance(
                                new TaxiRankProviderFromRetrofit(apiClient.getLocationsService()));
                    }
                }
        );
    }
}
