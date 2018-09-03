package com.big.fishcash.cash.presenter;

import com.big.fishcash.cash.bean.KnowledgeBean;
import com.big.fishcash.cash.contract.KnowledgeContract;
import com.big.fishcash.cash.model.modelinterface.IKnowledgeModel;
import com.big.fishcash.cash.network.MvpCallBack;
import com.big.fishcash.cash.ui.fragment.KnowledgeFragment;
import com.big.fishcash.cash.util.ToastUtil;

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


public class KnowlegePersenter extends BasePersenter<KnowledgeFragment> implements KnowledgeContract.IKnowledgePersenter {
    private  IKnowledgeModel iKnowledgeModel;

    public KnowlegePersenter(IKnowledgeModel iKnowledgeModel) {
        this.iKnowledgeModel = iKnowledgeModel;
    }

    @Override
    public void getKnowLedgeList() {
        if (!isAttachView()){
            return;
        }
        getMvpView().showLoadingDialog();
        iKnowledgeModel.getKnowledgeList(new MvpCallBack<KnowledgeBean>() {
            @Override
            public void onSuccess(KnowledgeBean knowledgeBean) {
                getMvpView().showKnowledgeList(knowledgeBean);
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
