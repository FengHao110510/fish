package com.big.fishcash.fishcash.base;

import android.app.Application;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;


import com.big.fishcash.fishcash.R;

import java.util.Iterator;
import java.util.Stack;
import java.util.concurrent.TimeUnit;





public class BaseApplication extends Application {

    public static BaseApplication app;


    /**
     * 蓝牙对象
     */
    public BluetoothSocket socket = null;

    /***寄存整个应用Activity**/
    private final Stack<AppCompatActivity> activitys = new Stack<AppCompatActivity>();


    @Override
    public void onCreate() {
        super.onCreate();
        initApp();
    }






    private void initApp() {
        app = this;
    }

    public static synchronized BaseApplication getInstance() {
        return app;
    }

    public static BaseApplication getApplication() {
        return app;
    }



    /**
     * 将Activity压入Application栈
     *
     * @param task 将要压入栈的Activity对象
     */
    public void pushTask(AppCompatActivity task) {
        activitys.push(task);
    }

    /**
     * 将传入的Activity对象从栈中移除
     *
     * @param task
     */
    public void removeTask(AppCompatActivity task) {
        if (task != null) {
            activitys.remove(task);
        }
    }

//    //设置 Header 为 MaterialHeader普通    BezierCircleHeader水滴  DropBoxHeader盒子
//        smTurnoverOrder.setRefreshHeader(new BezierCircleHeader(getActivity()));
//    //设置 Footer 为 经典样式  BallPulseFooter三个红点  丑   FalsifyFooter没效果
//        smTurnoverOrder.setRefreshFooter(new ClassicsFooter(getActivity()));

    //static 代码段可以防止内存泄露
//    static {
//        //设置全局的Header构建器
//        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
//            @Override
//            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
//                layout.setPrimaryColorsId(R.color.color_base_yellow, android.R.color.white);//全局设置主题颜色
//                return new MaterialHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
//            }
//        });
//        //设置全局的Footer构建器
//        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
//            @Override
//            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
//                //指定为经典Footer，默认是 BallPulseFooter
//                return new ClassicsFooter(context).setDrawableSize(16);
//            }
//        });
//    }

    /**
     * 根据指定位置从栈中移除Activity
     *
     * @param taskIndex Activity栈索引
     */
    public void removeTask(int taskIndex) {
        if (activitys.size() > taskIndex) {
            activitys.remove(taskIndex);
        }

    }

    /**
     * 将栈中Activity移除至栈顶
     */
    public void removeToTop() {
        int end = activitys.size();
        int start = 1;
        for (int i = end - 1; i >= start; i--) {
            if (!activitys.get(i).isFinishing()) {
                activitys.get(i).finish();
            }
        }
    }

    /**
     * 移除全部（用于整个应用退出）
     */
    public void removeAll() {
        //finish所有的Activity
        Iterator<AppCompatActivity> iterator = activitys.iterator();
        while (iterator.hasNext()) {
            AppCompatActivity task = iterator.next();
            if (!task.isFinishing()) {
                task.finish();
            }
        }
//        for (AppCompatActivity task : activitys) {
//            if (!task.isFinishing()) {
//                task.finish();
//            }
//        }
    }


    public static Context getAppContext() {
        return app.getApplicationContext();
    }

}