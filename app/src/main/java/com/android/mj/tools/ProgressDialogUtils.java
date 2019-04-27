package com.android.mj.tools;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.widget.ProgressBar;

import com.android.mj.R;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ChasingDots;
import com.github.ybq.android.spinkit.style.Circle;
import com.github.ybq.android.spinkit.style.CubeGrid;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.github.ybq.android.spinkit.style.FoldingCube;
import com.github.ybq.android.spinkit.style.Pulse;
import com.github.ybq.android.spinkit.style.RotatingCircle;
import com.github.ybq.android.spinkit.style.RotatingPlane;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.github.ybq.android.spinkit.style.Wave;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ProgressDialogUtils {

    private static ProgressDialog progressDialog;

    private static void initProgressDialog(Context context) {
        progressDialog = new LoadingProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
    }


    public static void showProgress(Context context) {
        try {
            initProgressDialog(context);
            progressDialog.show();
        } catch (Throwable e) {
            LogUtil.e("----------------------"+e.toString());
        }
    }

    public static void dismissProgress() {
        try {
            progressDialog.dismiss();
        } catch (Throwable e) {
        }
    }


}
