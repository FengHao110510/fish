package com.big.fishcash.cash.ui;

import android.content.Intent;
import android.os.Bundle;
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
import com.big.fishcash.cash.ui.activity.AboutWeActivity;
import com.big.fishcash.cash.ui.fragment.BaseFragment;
import com.big.fishcash.cash.ui.fragment.BooksFragment;
import com.big.fishcash.cash.ui.fragment.NewsFragment;
import com.big.fishcash.cash.util.FontHelper;
import com.big.fishcash.cash.util.ToastUtil;
import com.big.fishcash.cash.views.CircleImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.ll_main)
    LinearLayout llMain;
    @BindView(R.id.fl_main_fragment)
    FrameLayout flMainFragment;
    @BindView(R.id.tv_main_news_icon)
    TextView tvMainNewsIcon;
    @BindView(R.id.tv_main_news)
    TextView tvMainNews;
    @BindView(R.id.ll_main_news)
    LinearLayout llMainNews;
    @BindView(R.id.tv_main_books_icon)
    TextView tvMainBooksIcon;
    @BindView(R.id.tv_main_books)
    TextView tvMainBooks;
    @BindView(R.id.ll_main_books)
    LinearLayout llMainBooks;
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

    private ArrayList<BaseFragment> fragmentList;
    private BaseFragment tempFragment;//当前fragment
    //选中fragment对应的位置
    private int position = 0;

    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public int initLayout() {
        return R.layout.module_activity_main;
    }

    @Override
    protected void init() {

        initData();
        initFragment();
        initToolBar();
        initDrawerLayout();
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
                    case R.id.menu_main_item_share:
                        tip = "分享";
                        break;
                    default:
                        break;
                }
                ToastUtil.showToast(tip);
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

    @Override
    public void initData() {
        FontHelper.injectFont(llMain);
        llMainNews.performClick();
    }

    /**
     * @author fenghao
     * @date 2018/8/28 0028 上午 10:59
     * @desc 初始化fragment
     */
    private void initFragment() {
        fragmentList = new ArrayList<>();
        //新闻
        fragmentList.add(new NewsFragment());
        //书籍
        fragmentList.add(new BooksFragment());

    }


    @OnClick({R.id.ll_main_news, R.id.ll_main_books, R.id.ll_main_start_color, R.id.ll_main_start_night, R.id.ll_main_start_we})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_main_news:
                position = 0;
                setColor(tvMainNews, tvMainNewsIcon, tvMainBooks, tvMainBooksIcon);
                //设置logo
                //主标题
                toolMain.setTitle("新闻");
                toolMain.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
                break;
            case R.id.ll_main_books:
                position = 1;
                setColor(tvMainBooks, tvMainBooksIcon, tvMainNews, tvMainNewsIcon);
                //设置logo
                //主标题
                toolMain.setTitle("书籍");
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
    private void setColor(TextView t1, TextView t2, TextView t3, TextView t4) {
        t1.setTextColor(ContextCompat.getColor(this, R.color.pink));
        t2.setTextColor(ContextCompat.getColor(this, R.color.pink));
        t3.setTextColor(ContextCompat.getColor(this, R.color.gray));
        t4.setTextColor(ContextCompat.getColor(this, R.color.gray));
    }

    //====================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private long exitTime = 0;//计算点击时间

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
