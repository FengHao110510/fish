package com.big.fishcash.cash.adapter;

import android.support.annotation.Nullable;

import com.big.fishcash.cash.R;
import com.big.fishcash.cash.bean.SearchHistory;
import com.big.fishcash.cash.util.ColorUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/9/7 0007
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


public class SearchHistoryAdapter extends BaseQuickAdapter<SearchHistory,BaseViewHolder> {
    public SearchHistoryAdapter(int layoutResId, @Nullable List<SearchHistory> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchHistory item) {
        helper.setText(R.id.tv_item_search_history_name, item.getHistory())
                .setTextColor(R.id.tv_item_search_history_name,ColorUtil.getRandColorCode())
                .addOnClickListener(R.id.iv_item_search_history_delete);
    }
}
