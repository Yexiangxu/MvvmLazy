package com.lazyxu.mvvmlazy

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEachIndexed
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lazyxu.base.BuildConfig
import com.lazyxu.mvvmlazy.utils.ScreenStatusController
import com.lazyxu.mvvmlazy.utils.ScreenStatusListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var wallpaperFragment: HomeFragment? = null
    private var musicFragment: MineFragment? = null
    private var mFragments: MutableList<Fragment> = mutableListOf()
    private var lastIndex = 0
    var test = null

    private var mScreenStatusController: ScreenStatusController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        Handler().postDelayed({
            if (test == null) {
                throw Exception("抛出异常")
                Log.d("TestCrashTag", "正常运行了")
            }
        }, 3000)


    }

    //声明：
    private fun debug(code: () -> Unit) {
        if (BuildConfig.DEBUG) {
            code()
        }
    }


    private fun initView() {
        wallpaperFragment = HomeFragment()
        musicFragment = MineFragment()
        mFragments.add(wallpaperFragment!!)
        mFragments.add(musicFragment!!)
        mFragments.add(musicFragment!!)
        mFragments.add(musicFragment!!)
        mFragments.add(musicFragment!!)
        initNavigation()
        setFragmentPosition(0)

        initScreenListener()


    }

    private fun initScreenListener() {

        mScreenStatusController = ScreenStatusController(this)
        mScreenStatusController?.setScreenStatusListener(object : ScreenStatusListener {
            override fun onScreenOff() {
                Log.i("ScreenTest", "onScreenOff")
            }

            override fun onScreenOn() {
                Log.i("ScreenTest", "onScreenOn")
            }

            override fun userPresent() {
                Log.i("ScreenTest", "userPresent")
            }
        })
        mScreenStatusController?.startListen()

    }


    override fun onDestroy() {

        mScreenStatusController?.stopListen()
        super.onDestroy()
    }

    /**
     * 换肤 可根据不同节日等动态设置底部tab
     */

    private fun setSkinLoad() {
//       bottomNavigation.itemTextColor =ZMSkinManager.getInstance().skinResources?.getColorStateList(requireContext(), R.color.skin_main_tab_color)
    }

    private fun initNavigation() {
        bottomNavigation.itemIconTintList = null//设置自己图片(不设置会系统着色)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        bottomNavigation.menu.forEachIndexed { index, _item ->
            if (item.itemId == _item.itemId) {
                val drawable = item.icon
                if (drawable is Animatable) {
                    (drawable as Animatable).start()
                }
                setFragmentPosition(index)
            }
        }
        true
    }


    /**
     * commitAllowingStateLoss 和 commit 区别（尽量用commit）
     * 比如commit()操作在onSaveInstanceState()之后，可能抛出异常，
     * 那是不是直接所有都用 commitAllowingStateLoss 好呢？肯定不是
     *
     */
    private fun setFragmentPosition(position: Int) {
        try {
            val ft = supportFragmentManager.beginTransaction()
            val currentFragment = mFragments[position]
            val lastFragment = mFragments[lastIndex]
            lastIndex = position
            ft.hide(lastFragment)
            if (!currentFragment.isAdded) {
                supportFragmentManager.beginTransaction().remove(currentFragment).commitAllowingStateLoss()
                ft.add(R.id.tabNavigation, currentFragment)
            }
            ft.show(currentFragment)
            ft.commitAllowingStateLoss()
        } catch (e: IndexOutOfBoundsException) {
        }
    }

    private val clickListener = View.OnClickListener { }
}