package com.lazyxu.mvvmlazy.test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.lazyxu.base.base.BaseActivity
import com.lazyxu.base.base.head.HeadBuild
import com.lazyxu.base.base.head.HeadToolbar
import com.lazyxu.base.router.RouterUrl
import com.lazyxu.base.utils.SystemUtil
import com.lazyxu.mvvmlazy.R
@Route(path = RouterUrl.App.TEST)
class TestActivity : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
    }



    override fun onResume() {
        super.onResume()
        Log.d("HandlerTag", "${SystemUtil.isForeground(this, "com.lazyxu.mvvmlazy.test.TestActivity")}")

    }
}