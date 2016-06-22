package com.mcarr.optustechnicalevaluation.listitems;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mcarr.optustechnicalevaluation.R;

/**********************************
 * Created by Mikel on 22-Jun-16.
 *********************************/
public class MapItemView extends RecyclerView.ViewHolder {

    private TextView textView1;
    private TextView textView2;

    public MapItemView(View v) {
        super(v);
        textView1 = (TextView) v.findViewById(R.id.listitem_generic_map_1);
        textView2 = (TextView) v.findViewById(R.id.listitem_generic_map_2);
    }

    public static int getLayoutID() {
        return R.layout.listitem_generic_map;
    }

    public void setView(String value1, String value2) {
        this.textView1.setText(value1);
        this.textView2.setText(value2);
    }
}
