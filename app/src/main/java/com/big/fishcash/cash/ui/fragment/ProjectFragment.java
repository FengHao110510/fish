package com.big.fishcash.cash.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.big.fishcash.cash.R;
import com.big.fishcash.cash.bean.ProjectTabBean;
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


public class ProjectFragment extends BaseFragment {
    @BindView(R.id.tb_project_tab)
    TabLayout tbProjectTab;
    @BindView(R.id.vp_project)
    ViewPager vpProject;
    Unbinder unbinder;

    @Override
    public int initLayout() {
        return R.layout.module_fragment_project;
    }


    @Override
    public void init() {
        initTab();
//        initViewPager();
    }


    /**
     * @author fenghao
     * @date 2018/8/29 0029 下午 15:39
     * @desc 初始化tab
     */
    private List<ProjectTabBean> ProjectTabBeanList;

    private void initTab() {
        //返回头条，社会，国内，娱乐，体育，军事，科技，财经，时尚等新闻信息
        //类型,,top(头条，默认),shehui(社会),guonei(国内),guoji(国际),
        // yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
        ProjectTabBeanList = new ArrayList<>();
        String[] types = new String[]{
                "top", "shehui", "guonei", "guoji", "yule", "tiyu", "junshi", "keji", "caijing", "shishang"
        };
        String[] titles = new String[]{
                "头条", "社会", "国内", "国际", "娱乐", "体育", "军事", "科技", "财经", "时尚"
        };

        for (int i = 0; i < titles.length; i++) {
            ProjectTabBeanList.add(new ProjectTabBean(types[i], titles[i]));
        }
        //创建TAB
        for (int n = 1; n < ProjectTabBeanList.size(); n++) {
            TabLayout.Tab tab = tbProjectTab.newTab();
            tab.setText(ProjectTabBeanList.get(n).getTitle());
            tbProjectTab.addTab(tab);
        }

        tbProjectTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ToastUtil.showToast(tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /**
     * @author fenghao
     * @date 2018/8/29 0029 下午 16:46
     * @desc 初始化viewpager
     */
//    private List<ProjectContentFragment> fragmentList;
//
//    private void initViewPager() {
//        fragmentList = new ArrayList<>();
//        for (int i=0;i<ProjectTabBeanList.size();i++){
//            ProjectContentFragment ProjectContentFragment = new ProjectContentFragment();
//            Bundle bundle = new Bundle();
//            bundle.putString("type",ProjectTabBeanList.get(i).getType());
//            ProjectContentFragment.setArguments(bundle);
//            fragmentList.add(ProjectContentFragment);
//
//        }
//    }

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
