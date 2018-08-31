package com.big.fishcash.cash.adapter;

import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.big.fishcash.cash.R;
import com.big.fishcash.cash.bean.ArticleBean;
import com.big.fishcash.cash.network.FishClient;
import com.big.fishcash.cash.util.Global;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/8/31 0031
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


public class ArticleListAdapter extends BaseQuickAdapter<ArticleBean.DataBean.DatasBean, BaseViewHolder> {

    public ArticleListAdapter(int layoutResId, @Nullable List<ArticleBean.DataBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleBean.DataBean.DatasBean item) {

        helper.setText(R.id.tv_item_atricle_author, item.getAuthor())
                .setText(R.id.tv_item_atricle_nicedate, item.getNiceDate())
                .setText(R.id.tv_item_atricle_title, item.getTitle())
                .setText(R.id.tv_item_atricle_chaptername, item.getChapterName());
        //看看是否有图
        if (!TextUtils.isEmpty(item.getEnvelopePic())){
            Glide.with(mContext).load(item.getEnvelopePic()).placeholder(R.drawable.dg_logo)
                    .error(R.drawable.dg_logo).into((ImageView) helper.getView(R.id.iv_item_atricle_img));
            helper.setVisible(R.id.iv_item_atricle_img,true);
        }

        //如果是一天之内的显示
        if (!item.getNiceDate().contains("天")){
            helper.setVisible(R.id.tv_item_atricle_new,true);
        }
        if (item.isCollect()){
            helper.setImageResource(R.id.iv_item_atricle_collect,R.mipmap.xin1);
        }else {
            helper.setImageResource(R.id.iv_item_atricle_collect,R.mipmap.xin);

        }
    }
}
