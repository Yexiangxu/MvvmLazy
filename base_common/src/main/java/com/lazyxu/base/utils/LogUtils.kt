package com.lazyxu.base.utils


import android.content.Context
import android.util.Log
import com.lazyxu.base.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

object LogUtils {
    /**
     * 日志自动根据配置决定打开还是关闭
     */
    fun logAuto(context: Context) {
        if (BuildConfig.DEBUG) {//debug版本
            Timber.plant(Timber.DebugTree())
//            HttpLoggingInterceptor.logEnable = true
        } else {//release版本
            if (isLogEnable(context)) {
                Timber.plant(Timber.DebugTree())//打印开
//                HttpLoggingInterceptor.logEnable = true
            } else {
                Timber.plant(CrashReportingTree())//打印关，同时gradle中的release的debuggable要设置为false
//                HttpLoggingInterceptor.logEnable = false
            }
        }
        launch(context)
    }

    private fun launch(context: Context) {
        val sp = context.getSharedPreferences("log_config", Context.MODE_PRIVATE)
        val launchCount = sp.getInt("log_config_launch", 0)
        if (launchCount > 2) {
            sp.edit().putInt("log_config_launch", 0).apply()
        } else {
            sp.edit().putInt("log_config_launch", launchCount + 1).apply()
        }
    }

    /**
     * 设置日志是否可以开启
     */
    fun setLogEnable(context: Context, boolean: Boolean) {
        val sp = context.getSharedPreferences("log_config", Context.MODE_PRIVATE)
        sp.edit().putBoolean("log_config_open", boolean).apply()
        sp.edit().putInt("log_config_launch", 0).apply()
    }

    private fun isLogEnable(context: Context): Boolean {
        val sp = context.getSharedPreferences("log_config", Context.MODE_PRIVATE)
        val launchCount = sp.getInt("log_config_launch", 0)

        if (launchCount < 2) {
            return sp.getBoolean("log_config_open", false)
        }
        return false
    }

    private var tree: Timber.Tree? = null

    fun tag(tag: String): LogUtils {
        tree = Timber.tag(tag)

        return this
    }

    fun d(t: Throwable) {
        tree?.d(t) ?: Timber.d(t)
    }

    fun d(message: String, vararg args: Any) {
        tree?.d(message, *args) ?: Timber.d(message, args)
    }

    fun d(t: Throwable, message: String, vararg args: Any) {
        tree?.d(t, message, *args) ?: Timber.d(t, message, args)
    }


    fun e(t: Throwable) {
        tree?.d(t) ?: Timber.d(t)
    }

    fun e(message: String, vararg args: Any) {
        tree?.e(message, *args) ?: Timber.d(message, args)
    }

    fun e(t: Throwable, message: String, vararg args: Any) {
        tree?.e(t, message, *args) ?: Timber.d(t, message, args)
    }

    fun i(t: Throwable) {
        tree?.i(t) ?: Timber.i(t)
    }

    fun i(message: String, vararg args: Any) {
        tree?.i(message, *args) ?: Timber.i(message, args)
    }

    fun i(t: Throwable, message: String, vararg args: Any) {
        tree?.i(t, message, *args) ?: Timber.i(t, message, args)
    }


    private class CrashReportingTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return
            }
            //FakeCrashLibrary.log(priority, tag, message);
            if (t != null) {
                if (priority == Log.ERROR) {
                    //FakeCrashLibrary.logError(t);
                } else if (priority == Log.WARN) {
                    // FakeCrashLibrary.logWarning(t);
                } else {

                }
            }
        }
    }
}