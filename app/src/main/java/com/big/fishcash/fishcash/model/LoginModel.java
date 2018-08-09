package com.big.fishcash.fishcash.model;

import com.big.fishcash.fishcash.bean.LoginBean;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/8/9 0009
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


public class LoginModel implements ILoginModel {
    @Override
    public void getLoginData(final LoginModel.LoginDataCallback callback) {
        //数据获取操作，如数据库查询、网络加载等
        //数据获取操作，如数据库查询、网络加载等
        new Thread() {
            @Override
            public void run() {
                try {
                    //模拟耗时操作
                    Thread.sleep(3000);
                    //获取到了数据
                    String data = "我是获取到的数据";
                    //将获取的数据通过接口反馈出去
                    callback.success(data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    //获取数据失败的回调
                    callback.failure();
                }
            }
        }.start();
    }

    public interface LoginDataCallback {
        void success(String taskId);

        void failure();
    }
}
