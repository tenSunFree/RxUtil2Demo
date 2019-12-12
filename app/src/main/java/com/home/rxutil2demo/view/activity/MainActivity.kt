package com.home.rxutil2demo.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.home.rxutil2demo.R
import com.home.rxutil2demo.view.fragment.MainDetailFragment
import com.home.rxutil2demo.view.fragment.MainListFragment

class MainActivity : AppCompatActivity() {

    companion object {
        const val MAIN_LIST_FRAGMENT: String = "mainListFragment"
        const val MAIN_DETAIL_FRAGMENT: String = "mainDetailFragment"
    }

    var currentShowFragment: String? = null // 提供判斷目前activity是顯示哪一個fragment
    private var mainListFragment: MainListFragment? = null
    private var mainDetailFragment: MainDetailFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showFragment(MAIN_LIST_FRAGMENT)
    }

    /**
     * 顯示Fragment
     * 多個Fragment切換時, 避免重複創建Fragment
     */
    fun showFragment(fragmentName: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction() // 開啟事務
        hideFragment(fragmentTransaction)
        when (fragmentName) {
            MAIN_LIST_FRAGMENT ->
                if (mainListFragment == null) {
                    mainListFragment =
                        MainListFragment.newInstance(this)
                    fragmentTransaction.add(R.id.constraint_layout_container, mainListFragment!!)
                } else {
                    fragmentTransaction.show(mainListFragment!!)
                }
            MAIN_DETAIL_FRAGMENT ->
                if (mainDetailFragment == null) {
                    mainDetailFragment =
                        MainDetailFragment.newInstance(this)
                    fragmentTransaction.add(R.id.constraint_layout_container, mainDetailFragment!!)
                } else {
                    fragmentTransaction.show(mainDetailFragment!!)
                }
        }
        fragmentTransaction.commitAllowingStateLoss()
    }

    /**
     * 隱藏Fragment
     */
    private fun hideFragment(fragmentTransaction: FragmentTransaction) {
        if (mainListFragment != null) {
            fragmentTransaction.hide(mainListFragment!!)
        }
        if (mainDetailFragment != null) {
            fragmentTransaction.hide(mainDetailFragment!!)
        }
    }

    /**
     * 根據currentFragment判斷目前顯示的是哪一個Fragment, 並執行對應的行為
     */
    override fun onBackPressed() {
        when (currentShowFragment) {
            MAIN_LIST_FRAGMENT -> finish()
            MAIN_DETAIL_FRAGMENT -> showFragment(MAIN_LIST_FRAGMENT)
        }
    }
}
