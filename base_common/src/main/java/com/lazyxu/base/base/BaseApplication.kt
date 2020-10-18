package com.lazyxu.base.base

import android.app.Application
import android.content.Context
import android.os.Build
import android.text.TextUtils
import android.webkit.WebView
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.alibaba.android.arouter.launcher.ARouter
import com.lazyxu.base.BuildConfig
import com.lazyxu.base.Weak
import com.lazyxu.base.utils.LogUtils
import com.lazyxu.base.utils.MyCrashHandler
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException


abstract class BaseApplication : Application() {

    companion object {
        var INSTANCE by Weak<Application>()
        var refWatcher: RefWatcher? = null
    }

    override fun onCreate() {
        super.onCreate()
        LogUtils.logAuto(this)
        INSTANCE = this
        fixWebView()
        initSdk()
    }

    private fun initSdk() {
        //LeakCanary
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        if (BuildConfig.DEBUG) {
            Logger.addLogAdapter(AndroidLogAdapter())//后面删掉
            MyCrashHandler.getInstance().init(this)
            refWatcher = LeakCanary.install(this)
        }
        //Bugly

//        val deviceID = resources.getStringArray(R.array.developDeviceId)
//        val myId = Settings.System.getString(INSTANCE.contentResolver, Settings.Secure.ANDROID_ID)
//        if (!TextUtils.isEmpty(myId)) {
//            for (mID in deviceID) {
//                if (mID == myId) {
//                    Bugly.setIsDevelopmentDevice(applicationContext, true)
//                    break
//                }
//            }
//        }
//        val strategy = BuglyStrategy()
//        // 获取当前进程名
//        val processName = getProcessName(android.os.Process.myPid())
//        strategy.isUploadProcess = processName == null || processName == packageName
//        strategy.appChannel = Constants.QID
//        strategy.appVersion = Constants.VERSION
//        if (BuildConfig.DEBUG) {
//            Timber.tag("MyApplication").d("${Constants.UDI}_${Constants.UID}")
//            // 这两行必须写在init之前，否则这些配置在init过程中将无效
//            Bugly.init(INSTANCE, "a7d7948e8c", true, strategy)
//            Bugly.setIsDevelopmentDevice(applicationContext, true)
//            getTestDeviceInfo(this)
//        } else {
//            Bugly.init(INSTANCE, "77f1a9197c", false, strategy)
//            Bugly.setUserId(this, "${Constants.UDI}_${Constants.UID}")
//        }

        //ARouter
        if (BuildConfig.DEBUG) {
            ARouter.openLog()    // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(INSTANCE)

    }

    private fun fixWebView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val processName = getProcessName(android.os.Process.myPid())
            if (packageName != processName) {//判断不等于默认进程名称
                WebView.setDataDirectorySuffix(processName)
            }
        }
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    protected fun getProcessName(pid: Int): String? {
        var reader: BufferedReader? = null
        try {
            reader = BufferedReader(FileReader("/proc/$pid/cmdline"))
            var processName = reader.readLine()
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim({ it <= ' ' })
            }
            return processName
        } catch (throwable: Throwable) {
            //throwable.printStackTrace()
        } finally {
            try {
                if (reader != null) {
                    reader.close()
                }
            } catch (exception: IOException) {
                // exception.printStackTrace()
            }

        }
        return null
    }
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base)
    }

}