package com.android.tuan.tools;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.tuan.R;
import com.android.tuan.databinding.MainpopBinding;

/**
 * author: Rea.X
 * date: 2017/12/14.
 */

public class SelectTypeWindow {
    private static PopupWindow popupWindow;

    public static void popup(final Activity activity, View dropView, PopDismissCallback callback) {
        try {
            popupWindow = new PopupWindow();
            MainpopBinding popBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(activity), R.layout.mainpop, null, false);
            popBinding.tv0.setOnClickListener(new ClickImpl(1, callback));
            popBinding.tv1.setOnClickListener(new ClickImpl(2, callback));
            popBinding.tv2.setOnClickListener(new ClickImpl(3, callback));
            popBinding.tv3.setOnClickListener(new ClickImpl(4, callback));
            popBinding.tv4.setOnClickListener(new ClickImpl(5, callback));
            popBinding.tv5.setOnClickListener(new ClickImpl(6, callback));
            popBinding.tv6.setOnClickListener(new ClickImpl(7, callback));
            popupWindow.setContentView(popBinding.getRoot());
            popupWindow.setTouchable(true);
            popupWindow.setOutsideTouchable(true);
            Drawable drawable;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                drawable = activity.getDrawable(R.drawable.ic_main_pop_bg);
            } else {
                drawable = activity.getResources().getDrawable(R.drawable.ic_main_pop_bg);
            }
            popupWindow.setBackgroundDrawable(drawable);
            popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    popupWindow = null;
                }
            });
//
            popupWindow.showAsDropDown(dropView);
        } catch (Exception e) {
        }
    }

    public static void dismiss() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }

    private static class ClickImpl implements View.OnClickListener {
        private int position;
        private PopDismissCallback callback;

        public ClickImpl(int position, PopDismissCallback callback) {
            this.position = position;
            this.callback = callback;
        }

        @Override
        public void onClick(View view) {
            TextView textView = (TextView) view;
            callback.select(textView.getText().toString().trim(), position);
            dismiss();
        }
    }

    public static interface PopDismissCallback {
        void select(String text, int position);
    }
}
