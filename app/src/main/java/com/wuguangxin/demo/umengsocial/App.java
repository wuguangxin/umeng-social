package com.wuguangxin.demo.umengsocial;

import android.app.Application;

/**
 * Created by wuguangxin on 2020/8/19.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        UMengManager.init(this);
    }
}
