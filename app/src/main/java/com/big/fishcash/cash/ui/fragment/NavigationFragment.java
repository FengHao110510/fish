package com.big.fishcash.cash.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.big.fishcash.cash.R;
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
import q.rorbin.verticaltablayout.widget.QTabView;

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
        tabNavigation.setIndicatorWidth((int) tabNavigation.getX());
        for (int i = 0; i < dataBeanList.size(); i++) {
            QTabView qTabView = new QTabView(getContext());
            ITabView.TabTitle tabTitle = new QTabView.TabTitle.Builder().setContent(dataBeanList.get(i).getName())
                    .setTextColor(Color.BLACK, Color.GRAY)
                    .build();
            qTabView.setTitle(tabTitle);
            tabNavigation.addTab(qTabView);
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
