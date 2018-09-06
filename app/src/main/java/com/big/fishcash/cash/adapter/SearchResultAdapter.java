package com.big.fishcash.cash.adapter;

import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.widget.ImageView;

import com.big.fishcash.cash.R;
import com.big.fishcash.cash.bean.SearchResultBean;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/9/6 0006
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


public class SearchResultAdapter extends BaseQuickAdapter<SearchResultBean.DataBean.DatasBean, BaseViewHolder> {
    public SearchResultAdapter(int layoutResId, @Nullable List<SearchResultBean.DataBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchResultBean.DataBean.DatasBean item) {
        helper.setText(R.id.tv_item_atricle_author, item.getAuthor())
                .setText(R.id.tv_item_atricle_nicedate, item.getNiceDate())
                .setText(R.id.tv_item_atricle_chaptername, item.getChapterName())
                .addOnClickListener(R.id.iv_item_atricle_collect);
        if (item.getTitle().contains("class=")) {
            helper.setText(R.id.tv_item_atricle_title, Html.fromHtml(item.getTitle()));

        } else {
            helper.setText(R.id.tv_item_atricle_title, item.getTitle());

        }
        //看看是否有图
        if (!TextUtils.isEmpty(item.getEnvelopePic())) {
            Glide.with(mContext).load(item.getEnvelopePic()).placeholder(R.drawable.dg_logo)
                    .error(R.drawable.dg_logo).into((ImageView) helper.getView(R.id.iv_item_atricle_img));
            helper.setVisible(R.id.iv_item_atricle_img, true);
        }

        //如果是一天之内的显示
        if (item.getNiceDate().contains("小时")) {
            helper.setVisible(R.id.tv_item_atricle_new, true);
            helper.setVisible(R.id.iv_item_atricle_icon, false);
        } else {
            helper.setVisible(R.id.tv_item_atricle_new, false);
            helper.setVisible(R.id.iv_item_atricle_icon, true);

        }
        if (item.isCollect()) {
            helper.setImageResource(R.id.iv_item_atricle_collect, R.mipmap.xin1);
        } else {
            helper.setImageResource(R.id.iv_item_atricle_collect, R.mipmap.xin);

        }

    }
}
