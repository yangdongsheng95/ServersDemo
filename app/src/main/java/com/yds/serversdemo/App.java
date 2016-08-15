package com.yds.serversdemo;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Administrator on 2016/8/15.
 * 执行了这个类随后才会到主活动
 */
public class App extends Application {
    private static final String TAG = "App";

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化
        Log.d(TAG,"onCreate应用程序起点和入口");

        //启动服务
        startService(new Intent(App.this,LogService.class));
    }
}
