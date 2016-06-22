package com.mcarr.optustechnicalevaluation.retrofit;

import com.mcarr.optustechnicalevaluation.object.NavigationItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**********************************
 * Created by Mikel on 22-Jun-16.
 *********************************/
public interface IOptusAPI {

    @GET("sample.json")
    Call<ArrayList<NavigationItem>> getSample();

}
