package com.wimm.demo.wimmbtdemo;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/*
 * A custom ListView Class that exposes an isAtTop() method that
 * return true if the ListView is scrolled to the top.
 */

public class ConversationListView extends ListView {

    public ConversationListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean isAtTop() {
        return computeVerticalScrollOffset() == 0;
    }


}
