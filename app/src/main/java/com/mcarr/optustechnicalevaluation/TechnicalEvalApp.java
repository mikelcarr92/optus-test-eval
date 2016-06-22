package com.mcarr.optustechnicalevaluation;

import android.app.Application;

import com.mcarr.optustechnicalevaluation.otto.BusProvider;
import com.mcarr.optustechnicalevaluation.retrofit.ApiRequestHandler;
import com.squareup.otto.Bus;

/**********************************
 * Created by Mikel on 22-Jun-16.
 *********************************/
public class TechnicalEvalApp extends Application {

    public static ApiRequestHandler mApiRequestHandler;
    private Bus mBus = BusProvider.getInstance();

    @Override public void onCreate() {
        super.onCreate();
        mApiRequestHandler = new ApiRequestHandler(mBus);
        mBus.register(mApiRequestHandler);
    }
}
