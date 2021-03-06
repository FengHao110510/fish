package com.big.fishcash.cash.http;

import com.big.fishcash.cash.bean.ArticleBannerBean;
import com.big.fishcash.cash.bean.ArticleBean;
import com.big.fishcash.cash.bean.KnowledgeBean;
import com.big.fishcash.cash.bean.LoginBean;
import com.big.fishcash.cash.bean.MoreTagBean;
import com.big.fishcash.cash.bean.NavigationBean;
import com.big.fishcash.cash.bean.ProjectTabBean;
import com.big.fishcash.cash.bean.RegistBean;
import com.big.fishcash.cash.bean.HotSearchBean;
import com.big.fishcash.cash.bean.SearchResultBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/8/21 0021
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


public interface ApiService {
    /**
     * TODO 登录接口
     *
     * @param userName 账号
     * @param passWord 密码
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<LoginBean> tologin(@Field("username") String userName,
                                  @Field("password") String passWord);


    /**
     * TODO 注册接口
     *
     * @param userName 账号
     * @param passWord 密码
     */
    @FormUrlEncoded
    @POST("user/register")
    Observable<RegistBean> register(@Field("username") String userName,
                                    @Field("password") String passWord,
                                    @Field("repassword") String repassWord
    );

    /**
     * TODO 首页列表
     *
     * @author fenghao
     * @date 2018/8/31 0031 下午 12:13
     * @desc 首页列表接口
     */
    @GET("article/list/{page}/json")
    Observable<ArticleBean> articleList(@Path("page") int page);

    /**
     * TODO 首页banner列表
     *
     * @author fenghao
     * @date 2018/9/3 0003 上午 11:03
     * @desc 首页banner列表
     */
    @GET("banner/json")
    Observable<ArticleBannerBean> articleBannerList();

    /**
     * TODO 知识体系列表
     *
     * @author fenghao
     * @date 2018/9/3 0003 上午 11:03
     * @desc 知识体系列表
     */
    @GET("tree/json")
    Observable<KnowledgeBean> knowledgeList();

    /**
     * TODO 详细列表
     *
     * @author fenghao
     * @date 2018/9/4 0004 上午 11:35
     * @desc 知识体系详细列表
     */
    @GET("article/list/{page}/json")
    Observable<ArticleBean> contentList(@Path("page") int page, @Query("cid") int cid);

    /**
     * @author fenghao
     * @date 2018/9/4 0004 下午 17:42
     * @desc 导航列表
     */
    @GET("navi/json")
    Observable<NavigationBean> navigationList();

    /**
     * @author fenghao
     * @date 2018/9/4 0004 下午 16:23
     * @desc 项目列表Tab
     */
    @GET("project/tree/json")
    Observable<ProjectTabBean> projectTab();

    /**
     * TODO 详细列表
     *
     * @author fenghao
     * @date 2018/9/4 0004 上午 11:35
     * @desc 项目详细列表
     */
    @GET("project/list/{page}/json")
    Observable<ArticleBean> contentProjectList(@Path("page") int page, @Query("cid") int cid);

    /**
     * TODO 常用网站
     *
     * @author fenghao
     * @date 2018/9/6 0006 下午 14:35
     * @desc 常用网站
     */
    @GET("friend/json")
    Observable<MoreTagBean> moreTag();

    /**
     * TODO 热搜
     *
     * @author fenghao
     * @date 2018/9/6 0006 下午 16:42
     * @desc 热搜
     */
    @GET("hotkey/json")
    Observable<HotSearchBean> hotSearch();

    /**
     * TODO 关键词查询数据
     *
     * @author fenghao
     * @date 2018/9/6 0006 下午 17:37
     * @desc 获取关键词查询之后的数据
     */
    @FormUrlEncoded
    @POST("article/query/{page}/json")
    Observable<SearchResultBean> searchResult(@Path("page") int page, @Field("k") String content);
}
