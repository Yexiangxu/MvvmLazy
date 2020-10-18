package com.lazyxu.mvvmlazy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.core.view.forEachIndexed
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


/**
 * Created by luo on 2019/7/3.
 */
class MainActivity : AppCompatActivity() {
    private var wallpaperFragment: HomeFragment? = null
    private var musicFragment: MineFragment? = null
    private var myFragment: HomeFragment? = null
    private var myFragment4: HomeFragment? = null
    private var myFragment5: HomeFragment? = null
    private var mFragments: MutableList<Fragment> = mutableListOf()
    private var lastIndex = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        wallpaperFragment = HomeFragment()
        musicFragment = MineFragment()
        myFragment = HomeFragment()
        myFragment4 = HomeFragment()
        myFragment5 = HomeFragment()
        mFragments.add(wallpaperFragment!!)
        mFragments.add(wallpaperFragment!!)
        mFragments.add(wallpaperFragment!!)
        mFragments.add(wallpaperFragment!!)
        mFragments.add(wallpaperFragment!!)
        initNavigation()
        setFragmentPosition(0)

    }

    /**
     * 换肤 可根据不同节日等动态设置底部tab
     */

    private fun setSkinLoad() {
//       bottomNavigation.itemTextColor =ZMSkinManager.getInstance().skinResources?.getColorStateList(requireContext(), R.color.skin_main_tab_color)
    }

    private fun initNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        bottomNavigation.menu.forEachIndexed { index, _item ->
            if (item.itemId == _item.itemId) {
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

}