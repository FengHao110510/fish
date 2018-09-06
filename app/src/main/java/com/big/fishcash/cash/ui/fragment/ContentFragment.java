package com.big.fishcash.cash.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.big.fishcash.cash.R;
import com.big.fishcash.cash.adapter.ArticleListAdapter;
import com.big.fishcash.cash.bean.ArticleBean;
import com.big.fishcash.cash.bean.FABTNbean;
import com.big.fishcash.cash.contract.ContentContract;
import com.big.fishcash.cash.model.ContentModel;
import com.big.fishcash.cash.presenter.ContentPersenter;
import com.big.fishcash.cash.ui.activity.WebActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/8/29 0029
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


public class ContentFragment extends BaseFragment implements ContentContract.IContentView {
    @BindView(R.id.rv_content_list)
    RecyclerView rvContentList;
    Unbinder unbinder;
    @BindView(R.id.sm_content)
    SmartRefreshLayout smContent;

    //分页
    private int page;
    //获取数据需要的id 是上级id
    private int mCid;
    //判断哪来的 0项目 1知识体系
    private int where;
    private ContentPersenter contentPersenter;
    //列表适配器
    private ArticleListAdapter articleListAdapter;
    //数据源
    private List<ArticleBean.DataBean.DatasBean> datasBeanList;

    //项目分页居然从1开始
    private int page1 = 0;

    @Override
    public int initLayout() {
        return R.layout.module_fragment_content;
    }

    @Override
    public void init() {

        mCid = getArguments().getInt("cid");
        where = getArguments().getInt("where");
        if (where == 0) {
            page1 = 1;
        }
        page = page1;
        contentPersenter = new ContentPersenter(new ContentModel());
        contentPersenter.attachView(this);
        contentPersenter.getContentList(page, mCid, where);
        initPullRefresher();
    }

    /**
     * @author fenghao
     * @date 2018/9/3 0003 上午 10:31
     * @desc 初始化下拉刷新上拉加载
     */
    private void initPullRefresher() {
        //下拉刷新
        smContent.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = page1;
                contentPersenter.getContentList(page, mCid, where);
                smContent.finishRefresh();

            }
        });

        //上拉加载
        smContent.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                contentPersenter.getContentList(page, mCid, where);
                smContent.finishLoadMore();
            }
        });
    }

    @Override
    public void showContentList(ArticleBean articleBean) {
        if (articleListAdapter == null) {
            datasBeanList = articleBean.getData().getDatas();
            articleListAdapter = new ArticleListAdapter(R.layout.module_item_article_list, datasBeanList);
            rvContentList.setLayoutManager(new LinearLayoutManager(getContext()));
            rvContentList.setAdapter(articleListAdapter);
            articleListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    ImageView ivItemAtricleCollect = view.findViewById(R.id.iv_item_atricle_collect);
                    if (datasBeanList.get(position).isCollect()) {
                        datasBeanList.get(position).setCollect(false);
                        ivItemAtricleCollect.setImageResource(R.mipmap.xin);
                    } else {
                        datasBeanList.get(position).setCollect(true);
                        ivItemAtricleCollect.setImageResource(R.mipmap.xin1);
                    }
                }
            });
            articleListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent blogsIntent = new Intent(getActivity(), WebActivity.class);
                    blogsIntent.putExtra("title", datasBeanList.get(position).getTitle());
                    blogsIntent.putExtra("url", datasBeanList.get(position).getLink());
                    startActivity(blogsIntent);
                }
            });
        } else {
            showList(articleBean);
        }
    }

    /**
     * @param articleBean 列表数据源
     * @author fenghao
     * @date 2018/9/3 0003 上午 11:28
     * @desc 展示列表数据
     */
    private void showList(ArticleBean articleBean) {
        if (page == page1) {
            datasBeanList.clear();
        }
        datasBeanList.addAll(articleBean.getData().getDatas());
        articleListAdapter.notifyDataSetChanged();
    }

    @Subscribe
    public void onEventMainThread(FABTNbean fabtNbean) {
        if (fabtNbean.getPosition() == 3) {
            smoothMoveToPosition(rvContentList, 0);
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

    //=================================================================================================
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
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
