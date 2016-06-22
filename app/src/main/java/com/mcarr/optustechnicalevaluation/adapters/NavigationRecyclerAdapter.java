package com.mcarr.optustechnicalevaluation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mcarr.optustechnicalevaluation.listitems.MapItemView;

import java.util.ArrayList;
import java.util.Map;

/**********************************
 * Created by Mikel on 22-Jun-16.
 *********************************/
public class NavigationRecyclerAdapter extends RecyclerView.Adapter {

    private ArrayList<String> mKeys = new ArrayList<>();
    private Map<String, String> mData;

    public NavigationRecyclerAdapter(Map<String, String> listItems) {
        mKeys.clear();
        mKeys.addAll(listItems.keySet());
        mData = listItems;
    }

    public String getItem(int position) {
        return mKeys.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return mKeys.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(MapItemView.getLayoutID(), parent, false);
        return new MapItemView(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MapItemView viewHolder = ((MapItemView) holder);
        String key = getItem(position);
        viewHolder.setView(key, mData.get(key));
    }
}