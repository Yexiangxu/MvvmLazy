package com.lazyxu.base.utils

import android.app.ActivityManager
import android.content.Context
import android.text.TextUtils

object SystemUtil {
    fun isForeground(context: Context?, className: String): Boolean {
        if (context == null || TextUtils.isEmpty(className))
            return false
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val list = am.getRunningTasks(1)
        for (taskInfo in list) {
            if (taskInfo.topActivity?.className.toString().contains(className)) { // 说明它已经启动了
                return true
            }
        }
        return false
    }
}