package com.yds.serversdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    //服务的引用，但是绝对不能赋值
    MyService myService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainAty","活动创建");

//        myService=new MyService();错误的用法
    }

    public void doStart(View view) {
        //启动的服务
        startService(new Intent(this,MyService.class));
    }

    public void doStop(View view) {
        //停止服务
        stopService(new Intent(this, MyService.class));
    }

    public void doBind(View view) {
        //执行绑定
        //参数1：意图，定义了要绑定的服务
        //参数2：服务连接器
        //3：标识符：不存在则创建

        bindService(new Intent(this, MyService.class),
                conn,
                BIND_AUTO_CREATE);
    }

    public void doUnBind(View view) {
        //解除绑定
        unbindService(conn);
    }

    //服务连接器，其实就是一个监视器，监视服务的状态等
    //接口中两个都是回调方法
    ServiceConnection conn=new ServiceConnection() {
        /**
         * 绑定后执行，获得服务的引用
         * @param name
         * @param service
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("MyService","建立连接");

            //获得引用
            MyService.MyServiceBinder binder= (MyService.MyServiceBinder) service;
            myService=binder.getService();
        }

        /**
         * 服务意外结束
         * @param name
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("MyService","连接断开");

            myService=null;
        }
    };

    @Override
    protected void onDestroy() {
        Log.d("MainAty","活动销毁");

        super.onDestroy();
    }
}
