package com.android.mj.tools;

import android.text.TextUtils;

import com.android.mj.Library;

import static com.android.mj.cons.Cons.PrefrencesKeys.VERSION;

/**
 * author: Rea.X
 * date: 2017/12/2.
 */

public class GuideTools {

    public static boolean needShowGuide() {
        String saveVersion = PreferencesHelper.getString(VERSION);
        String version = CommonUtils.getVersionNameOriginal(Library.get());
        if (TextUtils.isEmpty(saveVersion)) return true;
        if (!saveVersion.equalsIgnoreCase(version)) return true;
        return false;
    }

    public static void guideDismiss() {
        PreferencesHelper.saveString(VERSION, CommonUtils.getVersionNameOriginal(Library.get()));
    }
}
