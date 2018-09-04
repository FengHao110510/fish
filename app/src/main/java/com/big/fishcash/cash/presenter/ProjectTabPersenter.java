package com.big.fishcash.cash.presenter;

import com.big.fishcash.cash.bean.ProjectTabBean;
import com.big.fishcash.cash.contract.ProjectContract;
import com.big.fishcash.cash.model.modelinterface.IProjectModel;
import com.big.fishcash.cash.network.MvpCallBack;
import com.big.fishcash.cash.ui.fragment.ProjectFragment;
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


public class ProjectTabPersenter extends BasePersenter<ProjectFragment> implements ProjectContract.IProjectPersenter {
    private IProjectModel iProjectModel;

    public ProjectTabPersenter(IProjectModel iProjectModel) {
        this.iProjectModel = iProjectModel;
    }

    @Override
    public void getProjectTab() {
        if (!isAttachView()) {
            return;
        }
        getMvpView().showLoadingDialog();
        iProjectModel.getProjectTab(new MvpCallBack<ProjectTabBean>() {
            @Override
            public void onSuccess(ProjectTabBean data) {
                getMvpView().showProjectTab(data);
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
