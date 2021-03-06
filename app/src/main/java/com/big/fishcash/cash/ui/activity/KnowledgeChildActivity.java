package com.big.fishcash.cash.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.big.fishcash.cash.R;
import com.big.fishcash.cash.adapter.ContentVPAdapter;
import com.big.fishcash.cash.base.BaseActivity;
import com.big.fishcash.cash.bean.FABTNbean;
import com.big.fishcash.cash.bean.KnowledgeBean;
import com.big.fishcash.cash.ui.fragment.ContentFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KnowledgeChildActivity extends BaseActivity {

    @BindView(R.id.tool_knowledge_child)
    Toolbar toolKnowledgeChild;
    @BindView(R.id.vp_konwledge_child)
    ViewPager vpKonwledgeChild;
    @BindView(R.id.tab_konwledge_child)
    TabLayout tabKonwledgeChild;
    @BindView(R.id.fabtn_konwledge_child)
    FloatingActionButton fabtnKonwledgeChild;
    //从上个页面传过来的 数据
    private KnowledgeBean.DataBean dataBean;
    private List<KnowledgeBean.DataBean.ChildrenBean> childrenBeanList;

    //fragmentList
    List<ContentFragment> fragmentList = new ArrayList<ContentFragment>();
    //vp适配器
    ContentVPAdapter contentVPAdapter;

    @Override
    public int initLayout() {
        return R.layout.module_activity_knowledge_child;
    }

    @Override
    public void init() {
        initData();
        initTooBar();
    }


    @Override
    public void initData() {
        dataBean = (KnowledgeBean.DataBean) getIntent().getSerializableExtra("dataBean");
        childrenBeanList = dataBean.getChildren();
        initViews();
    }

    /**
     * @author fenghao
     * @date 2018/9/4 0004 上午 9:59
     * @desc 初始化ToolBar
     */
    private void initTooBar() {

        toolKnowledgeChild.setTitle(dataBean.getName());
        toolKnowledgeChild.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        setSupportActionBar(toolKnowledgeChild);
        toolKnowledgeChild.setNavigationIcon(R.mipmap.back);

        toolKnowledgeChild.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishActivity();
            }
        });
    }

    /**
     * @author fenghao
     * @date 2018/9/4 0004 上午 9:59
     * @desc 初始化视图
     */
    private void initViews() {

        //循环注入标签
        for (int i = 0; i < childrenBeanList.size(); i++) {
            TabLayout.Tab tab = tabKonwledgeChild.newTab();
            tab.setText(childrenBeanList.get(i).getName());
            tabKonwledgeChild.addTab(tab);
            ContentFragment contentFragment = new ContentFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("cid", childrenBeanList.get(i).getId());
            bundle.putInt("where", 1);

            contentFragment.setArguments(bundle);
            fragmentList.add(contentFragment);

        }
        tabKonwledgeChild.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpKonwledgeChild.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        contentVPAdapter = new ContentVPAdapter(getSupportFragmentManager(), fragmentList);
        vpKonwledgeChild.setAdapter(contentVPAdapter);
        tabKonwledgeChild.setupWithViewPager(vpKonwledgeChild);
        //重新设置tabtitle 在setupWithViewPager之后
        for (int i = 0; i < childrenBeanList.size(); i++) {
            tabKonwledgeChild.getTabAt(i).setText(childrenBeanList.get(i).getName());
        }
        //预加载
        vpKonwledgeChild.setOffscreenPageLimit(childrenBeanList.size());
    }

    @OnClick(R.id.fabtn_konwledge_child)
    public void onViewClicked() {
        EventBus.getDefault().post(new FABTNbean(3));
    }

    //====================================================================================================
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
