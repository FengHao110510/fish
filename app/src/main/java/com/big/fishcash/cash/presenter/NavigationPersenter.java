package com.big.fishcash.cash.presenter;

import com.big.fishcash.cash.bean.NavigationBean;
import com.big.fishcash.cash.contract.NavigationContract;
import com.big.fishcash.cash.model.modelinterface.INavigationModel;
import com.big.fishcash.cash.network.MvpCallBack;
import com.big.fishcash.cash.ui.fragment.NavigationFragment;
import com.big.fishcash.cash.util.ToastUtil;

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


public class NavigationPersenter extends BasePersenter<NavigationFragment> implements NavigationContract.INavigationPersenter {
    private INavigationModel iNavigationModel;

    public NavigationPersenter(INavigationModel iNavigationModel) {
        this.iNavigationModel = iNavigationModel;
    }

    @Override
    public void getNavigationList() {
        if (!isAttachView()) {
            return;
        }
        getMvpView().showLoadingDialog();
        iNavigationModel.getNavigationList(new MvpCallBack<NavigationBean>() {
            @Override
            public void onSuccess(NavigationBean data) {
                getMvpView().showNavigationList(data);
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
