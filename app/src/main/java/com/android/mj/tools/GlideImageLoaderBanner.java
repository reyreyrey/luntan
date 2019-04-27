package com.android.mj.tools;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * author: Rea.X
 * date: 2017/12/13.
 */

public class GlideImageLoaderBanner extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        LogUtil.e("path::::::::::::::::::"+path.toString());
        Glide.with(context).load(path).into(imageView);
    }
}
