package com.big.fishcash.cash.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.big.fishcash.cash.R;
import com.big.fishcash.cash.adapter.ArticleListAdapter;
import com.big.fishcash.cash.bean.ArticleBean;
import com.big.fishcash.cash.contract.FirstContract;
import com.big.fishcash.cash.model.FirstModel;
import com.big.fishcash.cash.presenter.FirstPesenter;

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


public class FirstFragment extends BaseFragment implements FirstContract.IFirstView {

    @BindView(R.id.rv_first)
    RecyclerView rvFirst;
    Unbinder unbinder;
    private FirstPesenter firstPesenter;
    private int page;

    //首页列表list
    private ArticleListAdapter articleListAdapter;
    //首页列表数据源
    private List<ArticleBean.DataBean.DatasBean> datasBeanList;

    @Override
    public int initLayout() {
        return R.layout.module_fragment_first;
    }

    @Override
    public void init() {
        initData();
        initHeader();
    }

    /**
     * @author fenghao
     * @date 2018/8/31 0031 下午 17:43
     * @desc 初始化头部
     */
    private void initHeader() {

    }

    private void initData() {
        page = 0;
        firstPesenter = new FirstPesenter(new FirstModel());
        firstPesenter.attachView(this);
        firstPesenter.getArticleList(page);
        datasBeanList = new ArrayList<>();

    }


    @Override
    public void showArticleList(ArticleBean articleBean) {
        if (articleListAdapter == null) {
            datasBeanList = articleBean.getData().getDatas();
            articleListAdapter = new ArticleListAdapter(R.layout.module_item_article_list, datasBeanList);
            rvFirst.setLayoutManager(new LinearLayoutManager(getContext()));
            rvFirst.setAdapter(articleListAdapter);
        } else {
            showList(articleBean);
        }
    }


    private void showList(ArticleBean articleBean) {
        if (page == 0) {
            datasBeanList.clear();
        }
        datasBeanList.addAll(articleBean.getData().getDatas());
        articleListAdapter.notifyDataSetChanged();
    }

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
