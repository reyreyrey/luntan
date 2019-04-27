package com.android.tuan.tools;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;

import com.android.mj.Library;
import com.android.tuan.App;
import com.android.tuan.R;

/**
 * author: Rea.X
 * date: 2017/12/21.
 */

public class Tools {

    public static Drawable changeDrawableToDefaultTheme(Drawable drawable) {
        Drawable drawable1 = DrawableCompat.wrap(drawable).mutate();
        DrawableCompat.setTint(drawable1, Library.get().getResources().getColor(R.color.colorPrimary));
        return drawable1;
    }

    public static Drawable getTabButtonDrawable(@DrawableRes int res) {
        Drawable drawable = ContextCompat.getDrawable(Library.get(), res);
        int[] colors = new int[]{ContextCompat.getColor(Library.get(), R.color.colorPrimary), ContextCompat.getColor(Library.get(), R.color.color_nomal)};
        int[][] states = new int[2][];
        states[0] = new int[]{android.R.attr.state_checked};
        states[1] = new int[]{};
        ColorStateList colorList = new ColorStateList(states, colors);
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(states[0], drawable);//注意顺序
        stateListDrawable.addState(states[1], drawable);
        Drawable.ConstantState state = stateListDrawable.getConstantState();
        drawable = DrawableCompat.wrap(state == null ? stateListDrawable : state.newDrawable()).mutate();
        DrawableCompat.setTintList(drawable, colorList);
        return drawable;
    }
}
