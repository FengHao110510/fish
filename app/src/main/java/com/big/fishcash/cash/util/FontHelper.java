package com.big.fishcash.cash.util;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/8/25 0025
 * 描述：typeface帮助类
 * 修订历史：
 * ┌─┐       ┌─┐
 * ┌──┘ ┴───────┘ ┴──┐
 * │                 │
 * │       ───       │
 * │  ─┬┘       └┬─  │
 * │                 │
 * │       ─┴─       │
 * │                 │
 * └───┐         ┌───┘
 * │         │
 * │         │
 * │         │
 * │         └──────────────┐
 * │                        │
 * │                        ├─┐
 * │                        ┌─┘
 * │                        │
 * └─┐  ┐  ┌───────┬──┐  ┌──┘
 * │ ─┤ ─┤       │ ─┤ ─┤
 * └──┴──┘       └──┴──┘
 * 神兽保佑
 * 代码无BUG!
 */


public class FontHelper {
    public static final String DEF_FONT = "iconfont.ttf";

    public static final void injectFont(View rootView) {
        injectFont(rootView, Typeface.createFromAsset(rootView.getContext().getAssets(), DEF_FONT));
    }


    public static final void injectFont(View rootView, Typeface tf) {
        if (rootView instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) rootView;
            int count = viewGroup.getChildCount();
            for (int i = 0; i < count; i++) {
                injectFont(viewGroup.getChildAt(i), tf);
            }
        } else if (rootView instanceof TextView) {
            ((TextView) rootView).setTypeface(tf);
        }
    }
}
