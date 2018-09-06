package com.big.fishcash.cash.adapter;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.big.fishcash.cash.R;
import com.big.fishcash.cash.bean.KnowledgeBean;
import com.big.fishcash.cash.util.ColorUtil;
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
 * 创建日期：2018/9/3 0003
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


public class KnowledgeAdapter extends BaseQuickAdapter<KnowledgeBean.DataBean, BaseViewHolder> {
    public KnowledgeAdapter(int layoutResId, @Nullable List<KnowledgeBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, KnowledgeBean.DataBean item) {
        helper.setText(R.id.tv_item_knowledge_title, item.getName());

        final TagFlowLayout tflItemKnowledgeChild = helper.getView(R.id.tfl_item_knowledge_child);
        final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        tflItemKnowledgeChild.setAdapter(new TagAdapter<KnowledgeBean.DataBean.ChildrenBean>(item.getChildren()) {
            @Override
            public View getView(FlowLayout parent, int position, KnowledgeBean.DataBean.ChildrenBean childrenBean) {
                TextView moduleTextTagKnowlege = (TextView) layoutInflater.inflate(R.layout.module_text_tag_knowlege,
                        tflItemKnowledgeChild, false);
                moduleTextTagKnowlege.setText(childrenBean.getName());
                moduleTextTagKnowlege.setTextColor(ColorUtil.getRandColorCode());
                return moduleTextTagKnowlege;
            }

        });

    }
}
