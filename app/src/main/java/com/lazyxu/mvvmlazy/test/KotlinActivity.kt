package com.lazyxu.mvvmlazy.test

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lazyxu.mvvmlazy.R
import kotlinx.android.synthetic.main.activity_test.*

class KotlinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        tvMain.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
            }
        })
        tvMain.setOnClickListener(View.OnClickListener { view: View -> })
        tvMain.apply { }
        tvMain.let { }

        val result1 = resultByOpt(1, 2) { num1, num2 -> num1 + num2 }
    }

    private fun resultByOpt(num1: Int, num2: Int, result: (Int, Int) -> Int): Int {
        return result(num1, num2)
    }

}


