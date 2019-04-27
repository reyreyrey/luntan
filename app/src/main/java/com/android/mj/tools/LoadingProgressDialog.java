package com.android.mj.tools;

import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.android.mj.R;
import com.android.mj.databinding.DialogProgressBinding;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.RotatingPlane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class LoadingProgressDialog extends ProgressDialog {
    protected DialogProgressBinding databinding;
    protected List<SpinKitView> views;
    protected int random;
    private SpinKitView currentView;

    public LoadingProgressDialog(Context context) {
        super(context, R.style.progressDialogStyle);
        views = new ArrayList<>();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_progress, null, false);
        setCanceledOnTouchOutside(false);
        setContentView(databinding.getRoot());
        addViews();
    }

    private void addViews() {
        views.add(databinding.spinKit1);
        views.add(databinding.spinKit2);
        views.add(databinding.spinKit3);
        views.add(databinding.spinKit4);
        views.add(databinding.spinKit5);
        views.add(databinding.spinKit6);
        views.add(databinding.spinKit7);
        views.add(databinding.spinKit8);
        views.add(databinding.spinKit9);
        views.add(databinding.spinKit10);
        views.add(databinding.spinKit11);
        views.add(databinding.spinKit12);

        random = (int) (Math.random() * views.size());
        currentView = views.get(random);
        currentView.setVisibility(View.VISIBLE);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
