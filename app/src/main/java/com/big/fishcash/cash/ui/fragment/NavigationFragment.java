package com.big.fishcash.cash.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.big.fishcash.cash.R;
import com.big.fishcash.cash.adapter.NavigationAdapter;
import com.big.fishcash.cash.bean.NavigationBean;
import com.big.fishcash.cash.contract.NavigationContract;
import com.big.fishcash.cash.model.NavigationModel;
import com.big.fishcash.cash.presenter.NavigationPersenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.ITabView.TabBadge;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabBadgeView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/8/30 0030
 * 描述：导航fragment
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


public class NavigationFragment extends BaseFragment implements NavigationContract.INavigationView {
    @BindView(R.id.tab_navigation)
    VerticalTabLayout tabNavigation;
    @BindView(R.id.rv_navigation)
    RecyclerView rvNavigation;
    Unbinder unbinder;

    //Tab数据源
    List<NavigationBean.DataBean> dataBeanList;


    private NavigationPersenter navigationPersenter;

    private NavigationAdapter navigationAdapter;
    LinearLayoutManager linearLayoutManager;
    @Override
    public int initLayout() {
        return R.layout.module_fragment_navigation;
    }

    @Override
    public void init() {
        navigationPersenter = new NavigationPersenter(new NavigationModel());
        navigationPersenter.attachView(this);
        navigationPersenter.getNavigationList();
        dataBeanList = new ArrayList<>();
    }

    @Override
    public void showNavigationList(NavigationBean navigationBean) {
        /*
        属性说明
        xml code 说明
        app:indicator_color setIndicatorColor 指示器颜色
        app:indicator_width setIndicatorWidth 指示器宽度
        app:indicator_gravity setIndicatorGravity 指示器位置
        app:indicator_corners setIndicatorCorners 指示器圆角
        app:tab_mode setTabMode Tab高度模式
        app:tab_height setTabHeight Tab高度
        app:tab_margin setTabMargin Tab间距
         */
        dataBeanList = navigationBean.getData();
        initTab();
        initNavigationList();
    }


    /**
     * @author fenghao
     * @date 2018/9/5 0005 下午 16:42
     * @desc 初始化verticalTab
     */
    private void initTab() {
        for (int i = 0; i < dataBeanList.size(); i++) {
            QTabView qTabView = new QTabView(getContext());
            ITabView.TabTitle tabTitle = new QTabView.TabTitle.Builder().setContent(dataBeanList.get(i).getName())
                    .setTextColor(ContextCompat.getColor(getContext(), R.color.main_color), Color.GRAY)
                    .setTextSize(15)
                    .build();
            qTabView.setTitle(tabTitle);
            qTabView.setPadding(0, 30, 0, 30);
            tabNavigation.addTab(qTabView);
        }
        tabNavigation.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                smoothMoveToPosition(rvNavigation,position);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });
    }

    /**
     * @author fenghao
     * @date 2018/9/5 0005 下午 17:02
     * @desc 展示list
     */
    private void initNavigationList() {
        navigationAdapter = new NavigationAdapter(R.layout.module_item_navigation,dataBeanList);
         linearLayoutManager = new LinearLayoutManager(getContext());
        rvNavigation.setLayoutManager(linearLayoutManager);
        rvNavigation.setAdapter(navigationAdapter);
        rvNavigation.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });
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
    //==============================================================================================
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
