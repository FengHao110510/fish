package com.big.fishcash.cash.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.big.fishcash.cash.R;
import com.big.fishcash.cash.base.BaseActivity;
import com.big.fishcash.cash.base.BaseApplication;
import com.big.fishcash.cash.bean.FABTNbean;
import com.big.fishcash.cash.service.GeTuiIntentService;
import com.big.fishcash.cash.service.GeTuiServiceService;
import com.big.fishcash.cash.ui.activity.AboutWeActivity;
import com.big.fishcash.cash.ui.activity.LoginActivity;
import com.big.fishcash.cash.ui.activity.MoreActivity;
import com.big.fishcash.cash.ui.activity.SearchActivity;
import com.big.fishcash.cash.ui.fragment.BaseFragment;
import com.big.fishcash.cash.ui.fragment.FirstFragment;
import com.big.fishcash.cash.ui.fragment.KnowledgeFragment;
import com.big.fishcash.cash.ui.fragment.NavigationFragment;
import com.big.fishcash.cash.ui.fragment.ProjectFragment;
import com.big.fishcash.cash.util.FontHelper;
import com.big.fishcash.cash.util.Global;
import com.big.fishcash.cash.util.ToastUtil;
import com.big.fishcash.cash.views.CircleImageView;
import com.igexin.sdk.PushManager;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.fl_main_fragment)
    FrameLayout flMainFragment;
    @BindView(R.id.tool_main)
    Toolbar toolMain;
    @BindView(R.id.dl_mian)
    DrawerLayout dlMian;
    @BindView(R.id.iv_main_start_img)
    CircleImageView ivMainStartImg;
    @BindView(R.id.tv_main_start_name)
    TextView tvMainStartName;
    @BindView(R.id.ll_main_start_color)
    LinearLayout llMainStartColor;
    @BindView(R.id.ll_main_start_night)
    LinearLayout llMainStartNight;
    @BindView(R.id.ll_main_start_we)
    LinearLayout llMainStartWe;
    @BindView(R.id.ll_main_start)
    LinearLayout llMainStart;
    @BindView(R.id.ll_main_start_logout)
    LinearLayout llMainStartLogout;
    @BindView(R.id.tv_main_first_icon)
    TextView tvMainFirstIcon;
    @BindView(R.id.tv_main_first)
    TextView tvMainFirst;
    @BindView(R.id.ll_main_first)
    LinearLayout llMainFirst;
    @BindView(R.id.tv_main_knowledge_icon)
    TextView tvMainKnowledgeIcon;
    @BindView(R.id.tv_main_knowledge)
    TextView tvMainKnowledge;
    @BindView(R.id.ll_main_knowledge)
    LinearLayout llMainKnowledge;
    @BindView(R.id.tv_main_navigation_icon)
    TextView tvMainNavigationIcon;
    @BindView(R.id.tv_main_navigation)
    TextView tvMainNavigation;
    @BindView(R.id.ll_main_navigation)
    LinearLayout llMainNavigation;
    @BindView(R.id.tv_main_project_icon)
    TextView tvMainProjectIcon;
    @BindView(R.id.tv_main_project)
    TextView tvMainProject;
    @BindView(R.id.ll_main_project)
    LinearLayout llMainProject;
    @BindView(R.id.fabtn_main)
    FloatingActionButton fabtnMain;

    private ArrayList<BaseFragment> fragmentList;
    //当前fragment
    private BaseFragment tempFragment;
    //选中fragment对应的位置
    private int position = 0;

    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public int initLayout() {
        return R.layout.module_activity_main;
    }

    @Override
    public void init() {
        initData();
        initToolBar();
        initGeTui();
        initDrawerLayout();
        initFragment();

    }

    /**
     *  @date    2018/9/28 0028 下午 12:16
     *  @desc   初始化个推
     */
    private void initGeTui() {
        // com.getui.demo.DemoPushService 为第三方自定义推送服务
        PushManager.getInstance().initialize(getApplicationContext(), GeTuiServiceService.class);
        // com.getui.demo.DemoIntentService 为第三方自定义的推送服务事件接收类
        PushManager.getInstance().registerPushIntentService(getApplicationContext(), GeTuiIntentService.class);
    }

    @Override
    public void initData() {
        FontHelper.injectFont(dlMian);
        if (getIntent().hasExtra("userName")) {
            String userName = getIntent().getStringExtra("userName");
            tvMainStartName.setText(userName);
            ivMainStartImg.setImageResource(R.drawable.dg_logo);
        } else {
            llMainStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finishActivity();
                }
            });
        }
    }

    /**
     * @author fenghao
     * @date 2018/8/28 0028 下午 12:32
     * @desc 初始化toolbar
     */
    private void initToolBar() {
        //主标题
        toolMain.setTitle("新闻");
        toolMain.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        //设置toobar
        setSupportActionBar(toolMain);
        //左边的小箭头（注意需要在setSupportActionBar(toolbar)之后才有效果）
        toolMain.setNavigationIcon(R.mipmap.menu);
        //菜单点击事件（注意需要在setSupportActionBar(toolbar)之后才有效果）
        toolMain.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                String tip = "";
                switch (id) {
                    case R.id.menu_main_item_more:
                        startActivity(new Intent(MainActivity.this, MoreActivity.class));
                        break;
                    case R.id.menu_main_item_search:
                        startActivity(new Intent(MainActivity.this, SearchActivity.class));
                        break;
                    case R.id.menu_main_item_share:
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    /**
     * @author fenghao
     * @date 2018/8/28 0028 下午 16:35
     * @desc 初始化侧边栏
     */
    private void initDrawerLayout() {
        //设置变宽颜色
        ivMainStartImg.setBorderColor(ContextCompat.getColor(this, R.color.white));
        //创建返回键，并实现打开关/闭监听
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, dlMian, toolMain, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //侧滑栏关闭
            }
        };
        //mDrawerToggle.syncState();此处注释掉是为了不使用默认的开关箭头
        //设置侦听
        dlMian.addDrawerListener(actionBarDrawerToggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 绑定toobar跟menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    /**
     * @author fenghao
     * @date 2018/8/28 0028 上午 10:59
     * @desc 初始化fragment
     */
    private void initFragment() {
        fragmentList = new ArrayList<>();

        //首页
        fragmentList.add(new FirstFragment());
        //知识体系
        fragmentList.add(new KnowledgeFragment());
        //导航
        fragmentList.add(new NavigationFragment());
        //项目
        fragmentList.add(new ProjectFragment());
        //默认点击第一个
        llMainFirst.performClick();
    }

    @OnClick()
    public void onViewClicked() {
    }

    @OnClick({R.id.ll_main_first, R.id.ll_main_knowledge, R.id.ll_main_navigation,
            R.id.ll_main_project, R.id.ll_main_start_color, R.id.ll_main_start_night,
            R.id.ll_main_start_we, R.id.ll_main_start_logout, R.id.fabtn_main})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_main_first:
                position = 0;
                setColor(tvMainFirst, tvMainFirstIcon, tvMainKnowledge, tvMainKnowledgeIcon
                        , tvMainNavigation, tvMainNavigationIcon, tvMainProject, tvMainProjectIcon);
                //设置logo
                //主标题
                toolMain.setTitle("首页");
                toolMain.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
                break;
            case R.id.ll_main_knowledge:
                position = 1;
                setColor(tvMainKnowledge, tvMainKnowledgeIcon, tvMainFirst, tvMainFirstIcon
                        , tvMainNavigation, tvMainNavigationIcon, tvMainProject, tvMainProjectIcon);
                //设置logo
                //主标题
                toolMain.setTitle("知识体系");
                toolMain.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
                break;
            case R.id.ll_main_navigation:
                position = 2;
                setColor(tvMainNavigation, tvMainNavigationIcon, tvMainFirst, tvMainFirstIcon, tvMainKnowledge, tvMainKnowledgeIcon
                        , tvMainProject, tvMainProjectIcon);
                //设置logo
                //主标题
                toolMain.setTitle("导航");
                toolMain.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
                break;
            case R.id.ll_main_project:
                position = 3;
                setColor(tvMainProject, tvMainProjectIcon, tvMainKnowledge, tvMainKnowledgeIcon, tvMainFirst, tvMainFirstIcon
                        , tvMainNavigation, tvMainNavigationIcon);
                //设置logo
                //主标题
                toolMain.setTitle("项目");
                toolMain.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
                break;
            //侧滑的
            case R.id.ll_main_start_color:
                break;
            case R.id.ll_main_start_night:
                break;
            case R.id.ll_main_start_we:
                startActivity(new Intent(this, AboutWeActivity.class));
                break;
            case R.id.ll_main_start_logout:
                //退出登录
                Global.logout();
                startActivity(new Intent(this, LoginActivity.class));
                finishActivity();
                break;
            case R.id.fabtn_main:
                //悬浮按钮
                EventBus.getDefault().post(new FABTNbean(position));
                break;
            default:
                break;
        }

        BaseFragment nextFragment = getFragment(position);
        switchFragment(tempFragment, nextFragment);
    }

    /**
     * @param fromFragment 上一个显示的fragment
     * @param nextFragment 当前要显示的fragment
     * @author fenghao
     * @date 2018/8/28 0028 上午 11:10
     * @desc 选择fragment
     */
    private void switchFragment(BaseFragment fromFragment, BaseFragment nextFragment) {
        if (tempFragment != nextFragment) {
            tempFragment = nextFragment;

            if (nextFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.fl_main_fragment, nextFragment).commit();
                } else {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }

    //获取fragment
    private BaseFragment getFragment(int position) {
        if (fragmentList != null && fragmentList.size() > 0) {
            BaseFragment baseFragment = fragmentList.get(position);
            return baseFragment;
        }
        return null;
    }

    /**
     * @author fenghao
     * @date 2018/8/28 0028 上午 10:37
     * @desc 设置颜色
     */
    private void setColor(TextView t1, TextView t2, TextView t3, TextView t4
            , TextView t5, TextView t6, TextView t7, TextView t8) {
        t1.setTextColor(ContextCompat.getColor(this, R.color.main_color));
        t2.setTextColor(ContextCompat.getColor(this, R.color.main_color));
        t3.setTextColor(ContextCompat.getColor(this, R.color.gray));
        t4.setTextColor(ContextCompat.getColor(this, R.color.gray));
        t5.setTextColor(ContextCompat.getColor(this, R.color.gray));
        t6.setTextColor(ContextCompat.getColor(this, R.color.gray));
        t7.setTextColor(ContextCompat.getColor(this, R.color.gray));
        t8.setTextColor(ContextCompat.getColor(this, R.color.gray));
    }

    //====================================================================================================
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
    //计算点击时间
    private long exitTime = 0;

    /**
     * 点击两次退出app
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


    private void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtil.showToast(
                    "再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            BaseApplication.getInstance().removeAll();
            System.exit(0);
        }
    }


}
