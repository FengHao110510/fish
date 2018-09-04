package com.big.fishcash.cash.ui.fragment;

import android.media.tv.TvContentRating;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.big.fishcash.cash.R;
import com.big.fishcash.cash.adapter.ContentVPAdapter;
import com.big.fishcash.cash.bean.ProjectTabBean;
import com.big.fishcash.cash.contract.ProjectContract;
import com.big.fishcash.cash.model.ProjectModel;
import com.big.fishcash.cash.presenter.ProjectTabPersenter;
import com.big.fishcash.cash.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/8/28 0028
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


public class ProjectFragment extends BaseFragment implements ProjectContract.IProjectView {
    @BindView(R.id.tb_project_tab)
    TabLayout tbProjectTab;
    @BindView(R.id.vp_project)
    ViewPager vpProject;
    Unbinder unbinder;
    //vp适配器
    ContentVPAdapter contentVPAdapter;
    //fragmentList
    List<ContentFragment> fragmentList = new ArrayList<ContentFragment>();
    //Tab数据源
    private List<ProjectTabBean.DataBean> dataBeanList;
    private ProjectTabPersenter projectTabPersenter;
    @Override
    public int initLayout() {
        return R.layout.module_fragment_project;
    }


    @Override
    public void init() {
        projectTabPersenter = new ProjectTabPersenter(new ProjectModel());
        projectTabPersenter.attachView(this);
        projectTabPersenter.getProjectTab();
    }

    @Override
    public void showProjectTab(ProjectTabBean projectTabBean) {
        dataBeanList = projectTabBean.getData();
        initViews();
    }

    private void initViews() {
        for (int i=0;i<dataBeanList.size();i++){
            TabLayout.Tab tab = tbProjectTab.newTab();
            tab.setText(dataBeanList.get(i).getName());
            tbProjectTab.addTab(tab);
            ContentFragment contentFragment = new ContentFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("cid",dataBeanList.get(i).getId());
            bundle.putInt("where",0);
            contentFragment.setArguments(bundle);
//            fragmentList.add(ProjectContentFragment);
            fragmentList.add(contentFragment);
        }
        tbProjectTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpProject.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        contentVPAdapter = new ContentVPAdapter(getFragmentManager(),fragmentList);
        vpProject.setAdapter(contentVPAdapter);
        tbProjectTab.setupWithViewPager(vpProject);
        //重新设置tabtitle 在setupWithViewPager之后
        for (int i = 0; i < dataBeanList.size(); i++) {
            tbProjectTab.getTabAt(i).setText(dataBeanList.get(i).getName());
        }
        vpProject.setOffscreenPageLimit(dataBeanList.size()); //预加载
    }


    //================================================================================================
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
