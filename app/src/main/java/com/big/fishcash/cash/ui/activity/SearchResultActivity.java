package com.big.fishcash.cash.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.big.fishcash.cash.R;
import com.big.fishcash.cash.adapter.SearchResultAdapter;
import com.big.fishcash.cash.base.BaseActivity;
import com.big.fishcash.cash.bean.SearchResultBean;
import com.big.fishcash.cash.contract.SearchResultContract;
import com.big.fishcash.cash.model.SearchResultModel;
import com.big.fishcash.cash.presenter.SearchResultPersenter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultActivity extends BaseActivity implements SearchResultContract.ISearchResultView {

    @BindView(R.id.tool_search_result)
    Toolbar toolSearchResult;
    @BindView(R.id.rv_search_result_list)
    RecyclerView rvSearchResultList;
    @BindView(R.id.sm_search_result)
    SmartRefreshLayout smSearchResult;

    //传过来的要查找的内容
    private String content;

    //分页
    private int page;

    //数据
    List<SearchResultBean.DataBean.DatasBean> datasBeanList;
    private SearchResultAdapter searchResultAdapter;
    private SearchResultPersenter searchResultPersenter;

    @Override
    public int initLayout() {
        return R.layout.module_activity_search_result;
    }

    @Override
    public void init() {
        initData();
        initToolBar();
        initPullRefresher();

    }

    /**
     * @author fenghao
     * @date 2018/9/3 0003 上午 10:31
     * @desc 初始化下拉刷新上拉加载
     */
    private void initPullRefresher() {
        //下拉刷新
        smSearchResult.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                searchResultPersenter.getSearchResultList(content, page);
                smSearchResult.finishRefresh();
            }
        });

        //上拉加载
        smSearchResult.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                searchResultPersenter.getSearchResultList(content, page);
                smSearchResult.finishLoadMore();
            }
        });
    }

    @Override
    public void initData() {
        page = 0;
        content = getIntent().getStringExtra("content");
        searchResultPersenter = new SearchResultPersenter(new SearchResultModel());
        searchResultPersenter.attachView(this);
        searchResultPersenter.getSearchResultList(content, page);
    }

    /**
     * @author fenghao
     * @date 2018/9/6 0006 下午 17:26
     * @desc 初始化Toolbar
     */
    private void initToolBar() {
        toolSearchResult.setTitle(content);
        toolSearchResult.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        setSupportActionBar(toolSearchResult);
        toolSearchResult.setNavigationIcon(R.mipmap.back);
        toolSearchResult.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishActivity();
            }
        });
    }

    @Override
    public void showSearchResultList(SearchResultBean searchResultBean) {
        if (searchResultAdapter == null) {
            datasBeanList = searchResultBean.getData().getDatas();
            searchResultAdapter = new SearchResultAdapter(R.layout.module_item_article_list, datasBeanList);
            rvSearchResultList.setLayoutManager(new LinearLayoutManager(this));
            rvSearchResultList.setAdapter(searchResultAdapter);
            searchResultAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent blogsIntent = new Intent(SearchResultActivity.this, WebActivity.class);
                    blogsIntent.putExtra("title", datasBeanList.get(position).getTitle());
                    blogsIntent.putExtra("url", datasBeanList.get(position).getLink());
                    startActivity(blogsIntent);
                }
            });
        } else {
            if (page == 0) {
                datasBeanList.clear();
            }
            datasBeanList.addAll(searchResultBean.getData().getDatas());
            searchResultAdapter.notifyDataSetChanged();
        }


    }

    //====================================================================================================
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}