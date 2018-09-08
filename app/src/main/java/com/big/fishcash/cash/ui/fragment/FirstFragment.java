package com.big.fishcash.cash.ui.fragment;

import android.content.Context;
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
import com.big.fishcash.cash.bean.ArticleBannerBean;
import com.big.fishcash.cash.bean.ArticleBean;
import com.big.fishcash.cash.bean.FABTNbean;
import com.big.fishcash.cash.contract.FirstContract;
import com.big.fishcash.cash.model.FirstModel;
import com.big.fishcash.cash.presenter.FirstPesenter;
import com.big.fishcash.cash.ui.activity.MoreActivity;
import com.big.fishcash.cash.ui.activity.WebActivity;
import com.big.fishcash.cash.util.ToastUtil;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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
    @BindView(R.id.sm_first)
    SmartRefreshLayout smFirst;
    @BindView(R.id.rv_first)
    RecyclerView rvFirst;
    Unbinder unbinder;

    private FirstPesenter firstPesenter;
    private int page;

    //首页列表list
    private ArticleListAdapter articleListAdapter;
    //首页列表数据源
    private List<ArticleBean.DataBean.DatasBean> datasBeanList;
    //banner 图片资源
    List<String> listBannerImg;
    //banner名称
    List<String> titles;
    //banner地址
    List<String> links;
    //banner
    View view;
    Banner banner;

    @Override
    public int initLayout() {
        return R.layout.module_fragment_first;
    }

    @Override
    public void init() {
        initData();
        initPullRefresher();
    }

    /**
     * @author fenghao
     * @date 2018/9/3 0003 上午 10:31
     * @desc 初始化下拉刷新上拉加载
     */
    private void initPullRefresher() {
        //下拉刷新
        smFirst.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                firstPesenter.getArticleList(page);
                smFirst.finishRefresh();

            }
        });

        //上拉加载
        smFirst.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                firstPesenter.getArticleList(page);
                smFirst.finishLoadMore();
            }
        });
    }

    private void initData() {
        page = 0;
        firstPesenter = new FirstPesenter(new FirstModel());
        firstPesenter.attachView(this);
        firstPesenter.getArticleList(page);
        datasBeanList = new ArrayList<>();
        //放置图片的集合
        listBannerImg = new ArrayList<>();
        titles = new ArrayList<>();
        links = new ArrayList<>();

    }


    @Override
    public void showArticleList(ArticleBean articleBean) {
        if (articleListAdapter == null) {
            datasBeanList = articleBean.getData().getDatas();
            articleListAdapter = new ArticleListAdapter(R.layout.module_item_article_list, datasBeanList);
            rvFirst.setLayoutManager(new LinearLayoutManager(getContext()));
            rvFirst.setAdapter(articleListAdapter);
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
            firstPesenter.getBannerList();
        } else {
            showList(articleBean);
        }

    }

    /**
     * @param articleBean 首页列表数据源
     * @author fenghao
     * @date 2018/9/3 0003 上午 11:28
     * @desc 展示首页列表数据
     */
    private void showList(ArticleBean articleBean) {
        if (page == 0) {
            datasBeanList.clear();
            firstPesenter.getBannerList();
        }
        datasBeanList.addAll(articleBean.getData().getDatas());
        articleListAdapter.notifyDataSetChanged();
    }

    /**
     * @author fenghao
     * @date 2018/8/31 0031 下午 17:43
     * @desc 初始化头部
     */

    @Override
    public void showArticleBannerList(ArticleBannerBean articleBannerBean) {
        List<ArticleBannerBean.DataBean> data = articleBannerBean.getData();
        titles.clear();
        links.clear();
        listBannerImg.clear();
        for (int i = 0; i < data.size(); i++) {
            titles.add(data.get(i).getTitle());
            listBannerImg.add(data.get(i).getImagePath());
            links.add(data.get(i).getUrl());
        }
        if (view == null) {
            initBanner();
        } else {
            banner.notifyAll();
        }
        //回到顶部
        rvFirst.scrollToPosition(0);
    }

    private void initBanner() {
        view = getLayoutInflater().inflate(R.layout.module_item_header_first, null);
        banner = view.findViewById(R.id.bn_item_header);
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        banner.setImages(listBannerImg);
        banner.setBannerTitles(titles);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {

                        //轮播图点击事件
                    }
                })
                //必须最后调用的方法，启动轮播图。
                .start();
        articleListAdapter.removeAllHeaderView();
        articleListAdapter.addHeaderView(view);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent tagIntent = new Intent(getContext(), WebActivity.class);
                tagIntent.putExtra("title", titles.get(position));
                tagIntent.putExtra("url", links.get(position));
                startActivity(tagIntent);
            }
        });
    }

    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).dontAnimate().into(imageView);
        }
    }


    @Subscribe
    public void onEventMainThread(FABTNbean fabtNbean) {
        if (fabtNbean.getPosition()==0){
            smoothMoveToPosition(rvFirst,0);
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
    //================================================================================================
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
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
        unbinder.unbind();
    }
}
