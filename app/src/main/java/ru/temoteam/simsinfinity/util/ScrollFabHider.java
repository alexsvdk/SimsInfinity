package ru.temoteam.simsinfinity.util;

import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ScrollFabHider implements View.OnScrollChangeListener {
    FloatingActionButton fab;

    public ScrollFabHider(FloatingActionButton fab){
        this.fab = fab;
    }
    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (scrollY>oldScrollY)
            fab.hide();
        if (scrollY<oldScrollY)
            fab.show();
    }
}
