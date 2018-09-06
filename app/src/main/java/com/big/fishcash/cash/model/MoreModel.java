package com.big.fishcash.cash.model;

import com.big.fishcash.cash.bean.MoreTagBean;
import com.big.fishcash.cash.model.modelinterface.ImoreModel;
import com.big.fishcash.cash.network.FishClient;
import com.big.fishcash.cash.network.MvpCallBack;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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


public class MoreModel implements ImoreModel {

    @Override
    public void getMoreTag(final MvpCallBack<MoreTagBean> mvpCallBack) {
        FishClient.getFishRetrofitInstance().moreTag()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoreTagBean>() {
                    @Override
                    public void onCompleted() {
                        mvpCallBack.onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mvpCallBack.onError();
                    }

                    @Override
                    public void onNext(MoreTagBean moreTagBean) {
                        if (moreTagBean.getErrorCode() == 0) {
                            mvpCallBack.onSuccess(moreTagBean);
                        } else {
                            mvpCallBack.onFailure(moreTagBean.getErrorMsg());
                        }
                    }
                });
    }
}
