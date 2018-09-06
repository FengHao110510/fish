package com.big.fishcash.cash.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.big.fishcash.cash.R;
import com.big.fishcash.cash.base.BaseActivity;
import com.big.fishcash.cash.bean.MoreTagBean;
import com.big.fishcash.cash.contract.MoreContract;
import com.big.fishcash.cash.model.MoreModel;
import com.big.fishcash.cash.presenter.MorePersenter;
import com.big.fishcash.cash.util.ColorUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoreActivity extends BaseActivity implements MoreContract.IMoreView {

    @BindView(R.id.tool_more)
    Toolbar toolMore;
    @BindView(R.id.tfl_more)
    TagFlowLayout tflMore;

    private MorePersenter morePersenter;

    @Override
    public int initLayout() {
        return R.layout.module_activity_more;
    }

    @Override
    public void init() {
        initData();
        initToolBar();
    }

    @Override
    public void initData() {
        morePersenter = new MorePersenter(new MoreModel());
        morePersenter.attachView(this);
        morePersenter.getMoreTag();
    }


    /**
     * @author fenghao
     * @date 2018/9/6 0006 下午 14:25
     * @desc 初始化toolbar
     */
    private void initToolBar() {
        toolMore.setTitle("常用网站");
        toolMore.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        setSupportActionBar(toolMore);
        toolMore.setNavigationIcon(R.mipmap.back);
        toolMore.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishActivity();
            }
        });
    }


    @Override
    public void showMoreTag(MoreTagBean moreTagBean) {
        List<MoreTagBean.DataBean> dataBeanList = moreTagBean.getData();
        initTag(dataBeanList);
    }

    /**
     * @param dataBeanList 数据
     * @author fenghao
     * @date 2018/9/6 0006 下午 14:28
     * @desc 设置tag
     */
    private void initTag(final List<MoreTagBean.DataBean> dataBeanList) {
        final LayoutInflater layoutInflater = LayoutInflater.from(this);
        tflMore.setAdapter(new TagAdapter<MoreTagBean.DataBean>(dataBeanList) {
            @Override
            public View getView(FlowLayout parent, int position, MoreTagBean.DataBean dataBean) {
                TextView moduleTextTagMore = (TextView) layoutInflater.inflate(R.layout.module_text_tag_navigation, tflMore, false);
                moduleTextTagMore.setText(dataBean.getName());
                moduleTextTagMore.setBackgroundColor(ColorUtil.getRandColorCode());
                return moduleTextTagMore;
            }
        });
        tflMore.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Intent tagIntent = new Intent(MoreActivity.this, WebActivity.class);
                tagIntent.putExtra("title", dataBeanList.get(position).getName());
                tagIntent.putExtra("url", dataBeanList.get(position).getLink());
                startActivity(tagIntent);
                return false;
            }
        });
    }

    //===============================================================================================
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}