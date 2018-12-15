package com.big.fishcash.cash.util;

import android.content.Context;
import android.content.SharedPreferences;


public class SharePreferenceGlobalUtil {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public SharePreferenceGlobalUtil(Context context, String file) {
        sp = context.getSharedPreferences(file, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    /**
     * 版本号
     *
     * @param lastVersion
     */
    public void setLastVersion(String lastVersion) {
        editor.putString("Last_version", lastVersion);
        editor.commit();
    }

    public String getLastVersion() {
        return sp.getString("Last_version", "");
    }



/**
 * TODO =================================LOGIN========================================================
 */
    /**
     * 是否登录
     *
     * @param checkLogin
     */
    public void setCheckLogin(boolean checkLogin) {
        editor.putBoolean("checkLogin", checkLogin);
        editor.commit();
    }

    public boolean getCheckLogin() {
        return sp.getBoolean("checkLogin", false);
    }
    /**
     * 是否记住密码
     *
     * @param checkPassword
     */
    public void setCheckPassword(boolean checkPassword) {
        editor.putBoolean("checkPassword", checkPassword);
        editor.commit();
    }

    public boolean getCheckPassword() {
        return sp.getBoolean("checkPassword", true);
    }



    /**
     * 用户名字
     *
     * @param userName
     */
    public void setUserName(String userName) {
        editor.putString("userName", userName);
        editor.commit();
    }

    public String getUserName() {
        return sp.getString("userName", "");
    }
    /**
     * 账号
     *
     * @param user
     */
    public void setUser(String user) {
        editor.putString("user", user);
        editor.commit();
    }

    public String getUser() {
        return sp.getString("user", "");
    }
    /**
     * 密码
     *
     * @param password
     */
    public void setPassword(String password) {
        editor.putString("password", password);
        editor.commit();
    }

    public String getPassword() {
        return sp.getString("password", "");
    }


}