package com.big.fishcash.cash.model;

import com.big.fishcash.cash.bean.ProjectTabBean;
import com.big.fishcash.cash.model.modelinterface.IProjectModel;
import com.big.fishcash.cash.network.FishClient;
import com.big.fishcash.cash.network.MvpCallBack;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/9/4 0004
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


public class ProjectModel implements IProjectModel {
    @Override
    public void getProjectTab(final MvpCallBack<ProjectTabBean> mvpCallBack) {
        FishClient.getFishRetrofitInstance().projectTab()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProjectTabBean>() {
                    @Override
                    public void onCompleted() {
                        mvpCallBack.onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mvpCallBack.onError();
                    }

                    @Override
                    public void onNext(ProjectTabBean projectTabBean) {
                        if (projectTabBean.getErrorCode() == 0) {
                            mvpCallBack.onSuccess(projectTabBean);
                        } else {
                            mvpCallBack.onFailure(projectTabBean.getErrorMsg());
                        }
                    }
                });
    }
}
