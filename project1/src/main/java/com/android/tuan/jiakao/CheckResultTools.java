package com.android.tuan.jiakao;

/**
 * 创建时间：2019/4/17
 * 方法编写人：Rea.X
 * 功能描述：
 */

import android.text.TextUtils;
import android.widget.CheckBox;

/**
 * //答案对应："1": "A或者正确",
 * // "2": "B或者错误",
 * // "3": "C",
 * // "4": "D",
 * // "7": "AB",
 * // "8": "AC",
 * // "9": "AD",
 * // "10": "BC",
 * // "11": "BD",
 * // "12": "CD",
 * // "13": "ABC",
 * // "14": "ABD",
 * // "15": "ACD",
 * // "16": "BCD",
 * // "17": "ABCD"
 */
public class CheckResultTools {

    public static String getRightAnswer(String answer){
        if (TextUtils.isEmpty(answer)) return "";
        try {
            int ans = Integer.parseInt(answer);
            switch (ans) {
                case 1:
                    return "A/正确";
                case 2:
                    return "B/错误";
                case 3:
                    return "C";
                case 4:
                    return "D";
                case 7:
                    return "AB";
                case 8:
                    return "AC";
                case 9:
                    return "AD";
                case 10:
                    return "BC";
                case 11:
                    return "BD";
                case 12:
                    return "CD";
                case 13:
                    return "ABC";
                case 14:
                    return "ABD";
                case 15:
                    return "ACD";
                case 16:
                    return "BCD";
                case 17:
                    return "ABCD";
            }
        } catch (Throwable e) {
            return "";
        }
        return "";
    }

    public static boolean isRight(CheckBox cb1, CheckBox cb2, CheckBox cb3, CheckBox cb4, String answer) {
        if (TextUtils.isEmpty(answer)) return false;
        try {
            int ans = Integer.parseInt(answer);
            switch (ans) {
                case 1:
                    if (cb1.isChecked() && !cb2.isChecked() && !cb3.isChecked() && !cb4.isChecked())
                        return true;
                    return false;
                case 2:
                    if (!cb1.isChecked() && cb2.isChecked() && !cb3.isChecked() && !cb4.isChecked())
                        return true;
                    return false;
                case 3:
                    if (!cb1.isChecked() && !cb2.isChecked() && cb3.isChecked() && !cb4.isChecked())
                        return true;
                    return false;
                case 4:
                    if (!cb1.isChecked() && !cb2.isChecked() && !cb3.isChecked() && cb4.isChecked())
                        return true;
                    return false;
                case 7:
                    if (cb1.isChecked() && cb2.isChecked() && !cb3.isChecked() && !cb4.isChecked())
                        return true;
                    return false;
                case 8:
                    if (cb1.isChecked() && !cb2.isChecked() && cb3.isChecked() && !cb4.isChecked())
                        return true;
                    return false;
                case 9:
                    if (cb1.isChecked() && !cb2.isChecked() && !cb3.isChecked() && cb4.isChecked())
                        return true;
                    return false;
                case 10:
                    if (!cb1.isChecked() && cb2.isChecked() && cb3.isChecked() && !cb4.isChecked())
                        return true;
                    return false;
                case 11:
                    if (!cb1.isChecked() && cb2.isChecked() && !cb3.isChecked() && cb4.isChecked())
                        return true;
                    return false;
                case 12:
                    if (!cb1.isChecked() && !cb2.isChecked() && cb3.isChecked() && cb4.isChecked())
                        return true;
                    return false;
                case 13:
                    if (cb1.isChecked() && cb2.isChecked() && cb3.isChecked() && !cb4.isChecked())
                        return true;
                    return false;
                case 14:
                    if (cb1.isChecked() && cb2.isChecked() && !cb3.isChecked() && cb4.isChecked())
                        return true;
                    return false;
                case 15:
                    if (cb1.isChecked() && !cb2.isChecked() && cb3.isChecked() && cb4.isChecked())
                        return true;
                    return false;
                case 16:
                    if (!cb1.isChecked() && cb2.isChecked() && cb3.isChecked() && cb4.isChecked())
                        return true;
                    return false;
                case 17:
                    if (cb1.isChecked() && cb2.isChecked() && cb3.isChecked() && cb4.isChecked())
                        return true;
                    return false;
            }
        } catch (Throwable e) {
            return false;
        }
        return false;
    }
}
