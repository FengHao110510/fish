package com.big.fishcash.cash.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ContentFrameLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.big.fishcash.cash.R;
import com.big.fishcash.cash.bean.NewsTabBean;
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


public class NewsFragment extends BaseFragment {
    @BindView(R.id.tb_news_tab)
    TabLayout tbNewsTab;
    @BindView(R.id.vp_news)
    ViewPager vpNews;
    Unbinder unbinder;

    @Override
    public int initLayout() {
        return R.layout.moudle_fragment_news;
    }


    @Override
    public void init() {
        initTab();
        initViewPager();
    }


    /**
     * @author fenghao
     * @date 2018/8/29 0029 下午 15:39
     * @desc 初始化tab
     */
    private List<NewsTabBean> newsTabBeanList;

    private void initTab() {
        //返回头条，社会，国内，娱乐，体育，军事，科技，财经，时尚等新闻信息
        //类型,,top(头条，默认),shehui(社会),guonei(国内),guoji(国际),
        // yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
        newsTabBeanList = new ArrayList<>();
        String[] types = new String[]{
                "top", "shehui", "guonei", "guoji", "yule", "tiyu", "junshi", "keji", "caijing", "shishang"
        };
        String[] titles = new String[]{
                "头条", "社会", "国内", "国际", "娱乐", "体育", "军事", "科技", "财经", "时尚"
        };

        for (int i = 0; i < titles.length; i++) {
            newsTabBeanList.add(new NewsTabBean(types[i], titles[i]));
        }
        //创建TAB
        for (int n = 1; n < newsTabBeanList.size(); n++) {
            TabLayout.Tab tab = tbNewsTab.newTab();
            tab.setText(newsTabBeanList.get(n).getTitle());
            tbNewsTab.addTab(tab);
        }

        tbNewsTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
    private List<NewsContentFragment> fragmentList;

    private void initViewPager() {
        fragmentList = new ArrayList<>();
        for (int i=0;i<newsTabBeanList.size();i++){
            NewsContentFragment newsContentFragment = new NewsContentFragment();
            Bundle bundle = new Bundle();
            bundle.putString("type",newsTabBeanList.get(i).getType());
            newsContentFragment.setArguments(bundle);
            fragmentList.add(newsContentFragment);

        }
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
