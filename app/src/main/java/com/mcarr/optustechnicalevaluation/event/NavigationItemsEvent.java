package com.mcarr.optustechnicalevaluation.event;

import com.mcarr.optustechnicalevaluation.object.NavigationItem;

import java.util.ArrayList;

/**********************************
 * Created by Mikel on 22-Jun-16.
 *********************************/
public class NavigationItemsEvent extends BaseNetworkEvent {

    public static final OnLoadingError FAILED = new OnLoadingError(UNHANDLED_MSG, UNHANDLED_CODE);

    public static class OnLoaded extends OnDone<ArrayList<NavigationItem>> {
        public OnLoaded(ArrayList<NavigationItem> response) {
            super(response);
        }
    }

    public static class OnLoadingStart extends OnStart {
        public OnLoadingStart() {super(null);}
    }

    public static class OnLoadingError extends OnFailed {
        public OnLoadingError(String errorMessage, int code) {
            super(errorMessage, code);
        }
    }

}
