package com.lazyxu.mvvmlazy

import android.app.ActivityManager
import android.content.Context
import android.os.Build
import com.lazyxu.base.base.BaseApplication
import com.lazyxu.base.utils.LogUtils
import kotlin.properties.Delegates

class App : BaseApplication() {
    companion object {
        var instance: App by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        //注册activity生命周期

    }
    /**
     * 当应用存在多个进程时，确保只在主进程进行初始化
     */
    private fun isMainProcess(): Boolean {
        val pid = android.os.Process.myPid()
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        var isMainProcess = false
        if ((activityManager.runningAppProcesses?.size ?: 0) > 0) {
            for (appProcess in activityManager.runningAppProcesses) {
                if (appProcess.pid == pid) {
                    isMainProcess = packageName == appProcess.processName
                    break
                }
            }
        }
        LogUtils.tag("processCheck").d("process is main = $isMainProcess")
        if (!isMainProcess) {
            val processName = getProcessName(android.os.Process.myPid()).apply {
                LogUtils.tag("processCheck").d("process from file = $this")
            }
            isMainProcess = packageName == processName
        }

        if (!isMainProcess && Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            isMainProcess = getProcessName().apply {
                LogUtils.tag("processCheck").d("process from system = $this")
            } == packageName
        }
        return isMainProcess
    }

}