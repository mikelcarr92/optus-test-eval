package com.mcarr.optustechnicalevaluation.retrofit;

import com.mcarr.optustechnicalevaluation.event.NavigationItemsEvent;
import com.mcarr.optustechnicalevaluation.object.NavigationItem;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**********************************
 * Created by Mikel on 22-Jun-16.
 *********************************/
public class ApiRequestHandler {

    private Bus mBus;
    private ApiClient mApiClient;

    public ApiRequestHandler(Bus bus) {
        mBus = bus;
        mApiClient = ApiClient.getClient();
    }

    @Subscribe
    public void getNavigationItems (NavigationItemsEvent.OnLoadingStart onLoadingStart){

        mApiClient.getSmooviesApiService().getSample().enqueue(new Callback<ArrayList<NavigationItem>>() {
            @Override
            public void onResponse(Call<ArrayList<NavigationItem>> call, Response<ArrayList<NavigationItem>> response) {
                if (response.isSuccessful()) {
                    mBus.post(new NavigationItemsEvent.OnLoaded(response.body()));
                } else {
                    mBus.post(new NavigationItemsEvent.OnLoadingError("", 406));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<NavigationItem>> call, Throwable t) {
                mBus.post(new NavigationItemsEvent.OnLoadingError("error!", -1));
            }
        });
    }

}
