package com.big.fishcash.cash.base;


import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.Iterator;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class BaseApplication extends Application {

    public static BaseApplication app;


    /***寄存整个应用Activity**/
    private final Stack<AppCompatActivity> activitys = new Stack<AppCompatActivity>();


    @Override
    public void onCreate() {
        super.onCreate();
            app = this;
        settingOkHttp();

    }



    public static synchronized BaseApplication getInstance() {
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
    }


    public static Context getAppContext() {
        return app.getApplicationContext();
    }
    private void settingOkHttp() {
        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("HsFish", false))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .writeTimeout(10000L, TimeUnit.MILLISECONDS)
                .cookieJar(cookieJar).build();
        OkHttpUtils.initClient(okHttpClient);
    }
}