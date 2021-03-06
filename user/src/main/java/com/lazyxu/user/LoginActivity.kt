package com.lazyxu.user

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.lazyxu.base.base.BaseActivity
import com.lazyxu.base.base.head.HeadBuild
import com.lazyxu.base.base.head.HeadToolbar
import com.lazyxu.base.router.RouterUrl
import kotlinx.android.synthetic.main.activity_login.*


@Route(path = RouterUrl.User.LOGIN)
class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnLogin.setOnClickListener {
            val intent = Intent("wo.shi.outer.project.main.activity.action")
            startActivity(intent)
        }

    }

    override fun headToolbar(): HeadToolbar {
        return HeadBuild().toolbarTitle(R.string.login).create()
    }
}