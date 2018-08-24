package com.big.fishcash.cash.presenter;

import com.big.fishcash.cash.base.BaseIView;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/8/16 0016
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


public class BasePersenter<V extends BaseIView> {
    private V mvpView;

    public void attachView(V mvpView) {
        this.mvpView = mvpView;
    }

    public void detachView() {
        mvpView = null;
    }

    /**
     *  @author  fenghao
     *  @date    2018/8/16 0016 下午 14:47
     *  @return  绑定true 未绑定false
     *  @desc   判断persenter 是否绑定
     */
    public boolean isAttachView() {
        return mvpView != null;
    }

    public V getMvpView(){
        return mvpView;
    }

    /**
     * 检查view和presenter是否连接
     */
    public void checkViewAttach(){
        if(! isAttachView()){
            throw  new MvpViewNotAttachedException();
        }
    }
    /**
     * 自定义异常
     */
    public static   class  MvpViewNotAttachedException extends RuntimeException{
        public  MvpViewNotAttachedException(){
            super("请求数据前请先调用 attachView(MvpView) 方法与View建立连接");
        }
    }

}
