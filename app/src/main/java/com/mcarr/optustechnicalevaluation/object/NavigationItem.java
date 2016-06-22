package com.mcarr.optustechnicalevaluation.object;

import java.util.Map;

/**********************************
 * Created by Mikel on 22-Jun-16.
 *********************************/
public class NavigationItem {

    private int id;
    private String name;
    private Map<String, String> fromcentral;
    private LocationItem location;

    public NavigationItem() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getFromcentral() {
        return fromcentral;
    }

    public LocationItem getLocation() {
        return location;
    }
}
