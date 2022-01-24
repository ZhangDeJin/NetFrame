package com.zdj.demo.util;

import android.content.Context;

/**
 * <pre>
 *     author : dejinzhang
 *     time : 2022/01/24
 *     desc : Ui工具类
 * </pre>
 */
public class UiUtils {
    public static float dpToPx(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public static float pxToDp(Context context, float px) {
        float scale = context.getResources().getDisplayMetrics().density;
        return px / scale + 0.5f;
    }
}
