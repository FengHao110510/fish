package com.big.fishcash.cash.presenter;

import com.big.fishcash.cash.bean.MoreTagBean;
import com.big.fishcash.cash.contract.MoreContract;
import com.big.fishcash.cash.model.modelinterface.ImoreModel;
import com.big.fishcash.cash.network.MvpCallBack;
import com.big.fishcash.cash.ui.activity.MoreActivity;
import com.big.fishcash.cash.util.ToastUtil;

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


public class MorePersenter extends BasePersenter<MoreActivity> implements MoreContract.IMorePersenter {
    private ImoreModel imoreModel;

    public MorePersenter(ImoreModel imoreModel) {
        this.imoreModel = imoreModel;
    }

    @Override
    public void getMoreTag() {
        if (!isAttachView()) {
            return;
        }
        getMvpView().showLoadingDialog();
        imoreModel.getMoreTag(new MvpCallBack<MoreTagBean>() {
            @Override
            public void onSuccess(MoreTagBean data) {
                getMvpView().showMoreTag(data);
            }

            @Override
            public void onFailure(String msg) {
                ToastUtil.showToast(msg);
            }

            @Override
            public void onError() {
                getMvpView().dismissLoadingDialog();
            }

            @Override
            public void onComplete() {
                getMvpView().dismissLoadingDialog();

            }
        });
    }
}
