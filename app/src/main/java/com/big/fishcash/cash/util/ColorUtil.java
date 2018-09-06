package com.big.fishcash.cash.util;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.big.fishcash.cash.R;
import com.big.fishcash.cash.base.BaseApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/9/6 0006
 * 描述：
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


public class ColorUtil {

    /**
     * 获取十六进制的颜色代码.例如  "#6E36B4" , For HTML ,
     *
     * @return String shallow

     */
    public static int getRandColorCode() {
        String r, g, b;
        Random random = new Random();
        int color[] = {ContextCompat.getColor(BaseApplication.getAppContext(), R.color.list_blue)
                ,ContextCompat.getColor(BaseApplication.getAppContext(), R.color.list_pouple)
                ,ContextCompat.getColor(BaseApplication.getAppContext(), R.color.list_green)
                ,ContextCompat.getColor(BaseApplication.getAppContext(), R.color.list_brown)
                ,ContextCompat.getColor(BaseApplication.getAppContext(), R.color.list_orange)
                ,ContextCompat.getColor(BaseApplication.getAppContext(), R.color.list_yello)
        };


        return color[random.nextInt(6)];
    }


}
