package com.big.fishcash.cash.model;

import com.big.fishcash.cash.bean.HotSearchBean;
import com.big.fishcash.cash.model.modelinterface.ISearchModel;
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


public class SearchModel implements ISearchModel {
    @Override
    public void getHotSearch(final MvpCallBack<HotSearchBean> mvpCallBack) {
        FishClient.getFishRetrofitInstance().hotSearch()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotSearchBean>() {
                    @Override
                    public void onCompleted() {
                        mvpCallBack.onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mvpCallBack.onError();
                    }

                    @Override
                    public void onNext(HotSearchBean hotSearchBean) {
                        if (hotSearchBean.getErrorCode() == 0) {
                            mvpCallBack.onSuccess(hotSearchBean);
                        } else {
                            mvpCallBack.onFailure(hotSearchBean.getErrorMsg());
                        }
                    }
                });
    }
}
