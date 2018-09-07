package com.big.fishcash.cash.model;

import android.text.TextUtils;

import com.big.fishcash.cash.bean.HotSearchBean;
import com.big.fishcash.cash.bean.SearchHistory;
import com.big.fishcash.cash.model.modelinterface.ISearchModel;
import com.big.fishcash.cash.network.FishClient;
import com.big.fishcash.cash.network.MvpCallBack;

import org.litepal.crud.DataSupport;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/9/6 0006
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


public class SearchModel implements ISearchModel {
    @Override
    public void getHotSearch(final MvpCallBack<HotSearchBean> mvpCallBack) {
        FishClient.getFishRetrofitInstance().hotSearch()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotSearchBean>() {
                    @Override
                    public void onCompleted() {
                        mvpCallBack.onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mvpCallBack.onError();
                    }

                    @Override
                    public void onNext(HotSearchBean hotSearchBean) {
                        if (hotSearchBean.getErrorCode() == 0) {
                            mvpCallBack.onSuccess(hotSearchBean);
                        } else {
                            mvpCallBack.onFailure(hotSearchBean.getErrorMsg());
                        }
                    }
                });
    }

    @Override
    public void saveSeachHistory(String content) {
        List<SearchHistory> searchHistoryList = DataSupport.where("history=?", content).find(SearchHistory.class);
        if (searchHistoryList.size() < 1) {
            SearchHistory searchHistory = new SearchHistory();
            searchHistory.setHistory(content);
            searchHistory.save();
        }
    }

    @Override
    public void getSearchHistory(MvpCallBack<List<SearchHistory>> mvpCallBack) {
        mvpCallBack.onSuccess(DataSupport.findAll(SearchHistory.class));
    }

    @Override
    public void deleteSearchHistory(String content) {
        if (TextUtils.isEmpty(content)) {
            DataSupport.deleteAll(SearchHistory.class);
        } else {
            DataSupport.deleteAll(SearchHistory.class, "history=?", content);
        }
    }

}
