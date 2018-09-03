package com.big.fishcash.cash.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.big.fishcash.cash.R;
import com.big.fishcash.cash.base.BaseActivity;
import com.big.fishcash.cash.bean.KnowledgeBean;
import com.big.fishcash.cash.util.ToastUtil;

import java.io.Serializable;
import java.util.List;

public class KnowledgeChildActivity extends BaseActivity {

    //从上个页面传过来的 数据
    private KnowledgeBean.DataBean dataBean;
    private List<KnowledgeBean.DataBean.ChildrenBean> childrenBeanList;
    @Override
    public int initLayout() {
        return R.layout.module_activity_knowledge_child;
    }

    @Override
    protected void init() {
        initData();
    }


    @Override
    public void initData() {
        dataBean = (KnowledgeBean.DataBean) getIntent().getSerializableExtra("dataBean");
        childrenBeanList = dataBean.getChildren();

    }
}
