package com.big.fishcash.cash.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.big.fishcash.cash.R;
import com.big.fishcash.cash.base.BaseActivity;
import com.big.fishcash.cash.bean.HotSearchBean;
import com.big.fishcash.cash.contract.SearchContract;
import com.big.fishcash.cash.model.SearchModel;
import com.big.fishcash.cash.presenter.SearchPersenter;
import com.big.fishcash.cash.util.ColorUtil;
import com.big.fishcash.cash.util.FontHelper;
import com.big.fishcash.cash.util.ToastUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity implements SearchContract.ISearchView {

    @BindView(R.id.tv_search_back)
    TextView tvSearchBack;
    @BindView(R.id.et_search_search)
    EditText etSearchSearch;
    @BindView(R.id.tv_search_search)
    TextView tvSearchSearch;
    @BindView(R.id.tfl_search_hot)
    TagFlowLayout tflSearchHot;
    @BindView(R.id.ll_search_delete)
    LinearLayout llSearchDelete;
    @BindView(R.id.rv_search_history)
    RecyclerView rvSearchHistory;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;

    private SearchPersenter searchPersenter;

    @Override
    public int initLayout() {
        return R.layout.module_activity_search;
    }

    @Override
    public void init() {
        initData();
        FontHelper.injectFont(llSearch);
    }

    @Override
    public void initData() {
        searchPersenter = new SearchPersenter(new SearchModel());
        searchPersenter.attachView(this);
        searchPersenter.getHotSearch();
    }

    @Override
    public void showHotSearch(HotSearchBean searchHotBean) {
        final List<HotSearchBean.DataBean> dataBeanList = searchHotBean.getData();
        final LayoutInflater layoutInflater = LayoutInflater.from(this);
        tflSearchHot.setAdapter(new TagAdapter<HotSearchBean.DataBean>(dataBeanList) {
            @Override
            public View getView(FlowLayout parent, int position, HotSearchBean.DataBean dataBean) {
                TextView moduleTextTagNavigation = (TextView) layoutInflater.inflate(R.layout.module_text_tag_navigation, tflSearchHot, false);
                moduleTextTagNavigation.setBackgroundColor(ColorUtil.getRandColorCode());
                moduleTextTagNavigation.setText(dataBean.getName());
                return moduleTextTagNavigation;
            }
        });
        tflSearchHot.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Intent tagIntent = new Intent(SearchActivity.this, SearchResultActivity.class);
                tagIntent.putExtra("content", dataBeanList.get(position).getName());
                startActivity(tagIntent);
                return false;
            }
        });
    }

    @Override
    public void checkToIntent(boolean content) {
        if (content) {
            Intent resultIntent = new Intent(this, SearchResultActivity.class);
            resultIntent.putExtra("content", etSearchSearch.getText().toString());
            startActivity(resultIntent);
        } else {
            ToastUtil.showToast("请添加搜索关键词");
        }

    }

    @OnClick({R.id.tv_search_back, R.id.tv_search_search, R.id.ll_search_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_search_back:
                finishActivity();
                break;
            case R.id.tv_search_search:
                searchPersenter.checkSearchContent(etSearchSearch.getText().toString());
                break;
            case R.id.ll_search_delete:
                break;
            default:
                break;
        }
    }

    //=============================================================================================
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}