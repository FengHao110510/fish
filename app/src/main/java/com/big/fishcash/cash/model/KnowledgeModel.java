package com.big.fishcash.cash.model;

import android.util.Log;

import com.big.fishcash.cash.bean.KnowledgeBean;
import com.big.fishcash.cash.model.modelinterface.IKnowledgeModel;
import com.big.fishcash.cash.network.FishClient;
import com.big.fishcash.cash.network.MvpCallBack;
import com.big.fishcash.cash.util.ToastUtil;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/9/3 0003
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


public class KnowledgeModel implements IKnowledgeModel {
    @Override
    public void getKnowledgeList(final MvpCallBack<KnowledgeBean> mvpCallBack) {
        FishClient.getFishRetrofitInstance().knowledgeList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KnowledgeBean>() {
                    @Override
                    public void onCompleted() {
                        mvpCallBack.onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mvpCallBack.onError();
                    }

                    @Override
                    public void onNext(KnowledgeBean knowledgeBean) {
                        if (knowledgeBean.getErrorCode()==0){
                            mvpCallBack.onSuccess(knowledgeBean);
                        }else {
                            mvpCallBack.onFailure(knowledgeBean.getErrorMsg());
                        }
                    }
                });
    }
}
