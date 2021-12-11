package com.example.pruebas2;

import android.support.v4.view.ViewPager.LayoutParams;
//import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Helper {
    public static void getListViewSize(ListView listView) {
    	 ListAdapter listAdapter = listView.getAdapter();
    	    if (listAdapter == null)
    	        return;

    	    int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.UNSPECIFIED);
    	    int totalHeight = 0;
    	    View view = null;
    	    for (int i = 0; i < listAdapter.getCount(); i++) {
    	        view = listAdapter.getView(i, view, listView);
    	        if (i == 0)
    	            view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, LayoutParams.WRAP_CONTENT));

    	        view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
    	        totalHeight += view.getMeasuredHeight();
    	    }
    	    ViewGroup.LayoutParams params = listView.getLayoutParams();
    	    params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
    	    listView.setLayoutParams(params);
    	    listView.requestLayout();
    	}
}
