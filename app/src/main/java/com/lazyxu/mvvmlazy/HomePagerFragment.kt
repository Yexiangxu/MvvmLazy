package com.lazyxu.mvvmlazy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_homepager.*

class HomePagerFragment : Fragment() {
    companion object {
        fun getInstance(type: Int): Fragment {
            return HomePagerFragment().apply {
                arguments = Bundle().apply {
                    putInt("type", type)
                }
            }
        }
    }

    private val images = mutableListOf(
            R.drawable.default_home2,
            R.drawable.default_home3,
            R.drawable.default_home4,
            R.drawable.default_home1,
            R.drawable.default_home3,
            R.drawable.default_home4,
            R.drawable.default_home1,
            R.drawable.default_home3,
            R.drawable.default_home4,
            R.drawable.default_home1)

//    private val homePagerAdapter by lazy {
//        HomeTopAdapter()
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_homepager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        rvHomePager.apply {
//            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//            adapter = homePagerAdapter
//
//        }
//        homePagerAdapter.setNewInstance(images)
//        homePagerAdapter.setOnItemClickListener { adapter, view, position ->
//            Toast.makeText(activity, "点击了$position", Toast.LENGTH_SHORT).show()
//        }
    }
}