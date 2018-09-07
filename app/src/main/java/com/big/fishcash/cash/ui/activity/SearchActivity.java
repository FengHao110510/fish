package com.big.fishcash.cash.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.big.fishcash.cash.R;
import com.big.fishcash.cash.adapter.SearchHistoryAdapter;
import com.big.fishcash.cash.base.BaseActivity;
import com.big.fishcash.cash.bean.HotSearchBean;
import com.big.fishcash.cash.bean.SearchHistory;
import com.big.fishcash.cash.contract.SearchContract;
import com.big.fishcash.cash.model.SearchModel;
import com.big.fishcash.cash.presenter.SearchPersenter;
import com.big.fishcash.cash.util.ColorUtil;
import com.big.fishcash.cash.util.FontHelper;
import com.big.fishcash.cash.util.ToastUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
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

    //历史查询的adapter
    private SearchHistoryAdapter searchHistoryAdapter;
    private List<SearchHistory> searchHistoryList;

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
    protected void onResume() {
        searchPersenter.getSearchHistory();
        super.onResume();
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
                searchPersenter.saveSearchHistory(dataBeanList.get(position).getName());
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
            searchPersenter.saveSearchHistory(etSearchSearch.getText().toString());
        } else {
            ToastUtil.showToast("请添加搜索关键词");
        }

    }

    @Override
    public void showSearchHistory(List<SearchHistory> searchHistoryList1) {
        if (searchHistoryList != null) {
            searchHistoryList.clear();
        }
        this.searchHistoryList = searchHistoryList1;
        searchHistoryAdapter = new SearchHistoryAdapter(R.layout.module_item_search_history, searchHistoryList);
        rvSearchHistory.setLayoutManager(new LinearLayoutManager(this));
        rvSearchHistory.setAdapter(searchHistoryAdapter);
        searchHistoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent historyIntent = new Intent(SearchActivity.this, SearchResultActivity.class);
                historyIntent.putExtra("content", searchHistoryList.get(position).getHistory());
                startActivity(historyIntent);
            }
        });
        searchHistoryAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.iv_item_search_history_delete) {
                    searchPersenter.deleteSearchHistory(searchHistoryList.get(position).getHistory());
                    searchHistoryList.remove(position);
                    searchHistoryAdapter.notifyDataSetChanged();
                }
            }
        });
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
                searchPersenter.deleteSearchHistory("");
                searchHistoryList.clear();
                searchHistoryAdapter.notifyDataSetChanged();
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