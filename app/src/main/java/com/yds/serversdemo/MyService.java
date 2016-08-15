package com.yds.serversdemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * 服务
 */
public class MyService extends Service {
    MediaPlayer player;

    public void play(){

    }

    public void next(){

    }

    private static final String TAG = "MyService";

    /**
     * 创建
     * 只会执行一次
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate");
    }

    /**
     * 可以执行多次
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 销毁俯卧
     * 一次
     */
    @Override
    public void onDestroy() {
        Log.d(TAG,"onDestroy");
        super.onDestroy();
    }

    /**
     * 绑定的方法
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"onBind");

        //返回一个具体的东西，给服务连接，是给活动一个服务的一个引用
        return (IBinder) new MyServiceBinder();
    }

    /**
     * 解除绑定
     * @param intent
     * @return  返回ture，重新绑定才会执行
     */
    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG,"onUnbind");

        return true;
    }

    /**
     * 重新绑定
     * @param intent
     */
    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG,"onRebind");

        super.onRebind(intent);
    }

    class MyServiceBinder extends Binder {
        /**
         * 获得外部类的当前实例
         * @return
         */
        public MyService getService(){
            return  MyService.this;
        }

        public void next(){
            player.start();
        }

        public String sayHello(String name){
            return "Hello"+name;
        }
    }
}
