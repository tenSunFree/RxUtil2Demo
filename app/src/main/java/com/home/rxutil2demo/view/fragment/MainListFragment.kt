package com.home.rxutil2demo.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.home.rxutil2demo.R
import com.home.rxutil2demo.model.entity.MainListEntity
import com.home.rxutil2demo.presenter.MainListPresenter
import com.home.rxutil2demo.view.activity.MainActivity
import com.home.rxutil2demo.view.adapter.MainListAdapter
import com.xuexiang.rxutil2.rxbus.RxBusUtils
import kotlinx.android.synthetic.main.fragment_main_list.*

class MainListFragment : Fragment() {

    companion object {
        fun newInstance(activity: MainActivity): MainListFragment {
            val fragment = MainListFragment()
            fragment.activity = activity
            return fragment
        }

        const val MAIN_LIST_ENTITY: String = "mainListEntity"
    }

    lateinit var activity: MainActivity // lateinit表名變量需要在定義後才賦值

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setActivityCurrentShowFragmentLabel()
        initializeView()
    }

    private fun initializeView() {
        val presenter = MainListPresenter()
        val adapter = MainListAdapter()
        recycler_view.adapter = adapter
        adapter.updateData(presenter.getData())
        adapter.onItemClick = { onRecyclerViewItemClicked(it) }
    }

    private fun onRecyclerViewItemClicked(entity: MainListEntity) {
        activity.showFragment(MainActivity.MAIN_DETAIL_FRAGMENT)
        val delayMillis = 100L
        recycler_view.postDelayed({ RxBusUtils.get().post(MAIN_LIST_ENTITY, entity) }, delayMillis)
    }

    /** 告訴Activity, 目前顯示的Fragment是哪一個 */
    private fun setActivityCurrentShowFragmentLabel() {
        activity.currentShowFragment = MainActivity.MAIN_LIST_FRAGMENT
    }

    /**
     * 判斷此Fragment目前屬於顯示還是隱藏
     * 第二次顯示之後, 每次都會觸發此方法; 第一次顯示, 只會觸發onCreateView
     */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            setActivityCurrentShowFragmentLabel()
        }
    }
}