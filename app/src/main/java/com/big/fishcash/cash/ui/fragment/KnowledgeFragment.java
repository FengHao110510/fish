package com.big.fishcash.cash.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.big.fishcash.cash.R;
import com.big.fishcash.cash.adapter.KnowledgeAdapter;
import com.big.fishcash.cash.bean.FABTNbean;
import com.big.fishcash.cash.bean.KnowledgeBean;
import com.big.fishcash.cash.contract.KnowledgeContract;
import com.big.fishcash.cash.model.KnowledgeModel;
import com.big.fishcash.cash.presenter.KnowlegePersenter;
import com.big.fishcash.cash.ui.activity.KnowledgeChildActivity;
import com.big.fishcash.cash.ui.activity.WebActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/8/30 0030
 * 描述：知识体系啊fragment
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


public class KnowledgeFragment extends BaseFragment implements KnowledgeContract.IKnowledgeView {
    @BindView(R.id.rv_knowledge)
    RecyclerView rvKnowledge;
    Unbinder unbinder;
    @BindView(R.id.sm_knowledge)
    SmartRefreshLayout smKnowledge;

    private KnowledgeAdapter knowledgeAdapter;
    private KnowlegePersenter knowlegePersenter;

    //数据源
    private List<KnowledgeBean.DataBean> dataBeanList;

    @Override
    public int initLayout() {
        return R.layout.module_fragment_knowledge;
    }

    @Override
    public void init() {
        dataBeanList = new ArrayList<>();

        knowlegePersenter = new KnowlegePersenter(new KnowledgeModel());
        knowlegePersenter.attachView(this);
        knowlegePersenter.getKnowLedgeList();
        initPullRefresher();

    }

    /**
     * @author fenghao
     * @date 2018/9/3 0003 下午 18:20
     * @desc 初始化上拉加载下拉刷新
     */
    private void initPullRefresher() {
        smKnowledge.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                knowlegePersenter.getKnowLedgeList();
                smKnowledge.finishRefresh();
            }
        });
    }

    @Override
    public void showKnowledgeList(KnowledgeBean knowledgeBean) {
        dataBeanList.clear();
        dataBeanList = knowledgeBean.getData();
        knowledgeAdapter = new KnowledgeAdapter(R.layout.module_item_knowledge, dataBeanList);
        rvKnowledge.setLayoutManager(new LinearLayoutManager(getContext()));
        rvKnowledge.setAdapter(knowledgeAdapter);

        knowledgeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent childKnowledgeIntent = new Intent(getActivity(), KnowledgeChildActivity.class);
                childKnowledgeIntent.putExtra("dataBean", (Serializable) dataBeanList.get(position));
                startActivity(childKnowledgeIntent);
            }
        });
    }

    @Subscribe
    public void onEventMainThread(FABTNbean fabtNbean) {
        if (fabtNbean.getPosition() == 1) {
            smoothMoveToPosition(rvKnowledge, 0);
        }
    }

    //目标项是否在最后一个可见项之后
    private boolean mShouldScroll;
    //记录目标项位置
    private int mToPosition;

    /**
     * 滑动到指定位置
     */
    private void smoothMoveToPosition(RecyclerView mRecyclerView, final int position) {
        // 第一个可见位置
        int firstItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0));
        // 最后一个可见位置
        int lastItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1));
        if (position < firstItem) {
            // 第一种可能:跳转位置在第一个可见位置之前，使用smoothScrollToPosition
            mRecyclerView.smoothScrollToPosition(position);
        } else if (position <= lastItem) {
            // 第二种可能:跳转位置在第一个可见位置之后，最后一个可见项之前
            int movePosition = position - firstItem;
            if (movePosition >= 0 && movePosition < mRecyclerView.getChildCount()) {
                int top = mRecyclerView.getChildAt(movePosition).getTop();
                // smoothScrollToPosition 不会有效果，此时调用smoothScrollBy来滑动到指定位置
                mRecyclerView.smoothScrollBy(0, top);
            }
        } else {
            // 第三种可能:跳转位置在最后可见项之后，则先调用smoothScrollToPosition将要跳转的位置滚动到可见位置
            // 再通过onScrollStateChanged控制再次调用smoothMoveToPosition，执行上一个判断中的方法
            mRecyclerView.smoothScrollToPosition(position);
            mToPosition = position;
            mShouldScroll = true;
        }
    }

    //===============================================================================================
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);

        unbinder.unbind();
    }


}
