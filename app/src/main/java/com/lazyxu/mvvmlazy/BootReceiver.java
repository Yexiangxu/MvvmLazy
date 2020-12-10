package com.lazyxu.mvvmlazy;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import static com.tencent.bugly.Bugly.applicationContext;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){
        //接收安装广播
        if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
            String packageName = intent.getDataString();
            Log.i("BootReceiverTag","安装了:" +packageName + "包名的程序");
        }
        //接收卸载广播
        if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
            String packageName = intent.getDataString();
            Log.i("BootReceiverTag","卸载了:"  + packageName + "包名的程序");
        }
        if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
            String packageName = intent.getDataString();
            Log.i("BootReceiverTag","卸载了:"  + packageName + "包名的程序");
        }

    }
}
