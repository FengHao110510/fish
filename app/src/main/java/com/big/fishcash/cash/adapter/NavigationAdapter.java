package com.big.fishcash.cash.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.big.fishcash.cash.R;
import com.big.fishcash.cash.bean.KnowledgeBean;
import com.big.fishcash.cash.bean.NavigationBean;
import com.big.fishcash.cash.ui.activity.WebActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/9/5 0005
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


public class NavigationAdapter extends BaseQuickAdapter<NavigationBean.DataBean, BaseViewHolder> {
    public NavigationAdapter(int layoutResId, @Nullable List<NavigationBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final NavigationBean.DataBean item) {
        helper.setText(R.id.tv_item_navigation_title,item.getName());
        final TagFlowLayout tflItemNavigationChild = helper.getView(R.id.tfl_item_navigation_child);
        final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        tflItemNavigationChild.setAdapter(new TagAdapter<NavigationBean.DataBean.ArticlesBean>(item.getArticles()) {
            @Override
            public View getView(FlowLayout parent, int position, NavigationBean.DataBean.ArticlesBean articlesBean) {
                TextView moduleTextTagKnowlege = (TextView) layoutInflater.inflate(R.layout.module_text_tag_navigation, tflItemNavigationChild, false);
                moduleTextTagKnowlege.setText(articlesBean.getTitle());
                return moduleTextTagKnowlege;
            }
        });
        tflItemNavigationChild.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                item.getArticles().get(position).getLink();
                Intent tagIntent = new Intent(mContext, WebActivity.class);
                tagIntent.putExtra("title", item.getArticles().get(position).getTitle());
                tagIntent.putExtra("url", item.getArticles().get(position).getLink());
                mContext.startActivity(tagIntent);
                return false;
            }
        });
    }
}
