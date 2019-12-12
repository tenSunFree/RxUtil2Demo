package com.home.rxutil2demo.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.api.load
import com.home.rxutil2demo.R
import com.home.rxutil2demo.model.entity.MainListEntity
import com.home.rxutil2demo.view.activity.MainActivity
import com.xuexiang.rxutil2.rxbus.RxBusUtils
import kotlinx.android.synthetic.main.fragment_main_detail.*

class MainDetailFragment : Fragment() {

    companion object {
        fun newInstance(activity: MainActivity): MainDetailFragment {
            val fragment = MainDetailFragment()
            fragment.activity = activity
            return fragment
        }
    }

    private lateinit var activity: MainActivity // lateinit表名變量需要在定義後才賦值

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setActivityCurrentShowFragmentLabel()
        initializeView()
    }

    /** 告訴Activity, 目前顯示的Fragment是哪一個 */
    private fun setActivityCurrentShowFragmentLabel() {
        activity.currentShowFragment =
            MainActivity.MAIN_DETAIL_FRAGMENT
    }

    private fun initializeView() {
        RxBusUtils.get().onMainThread(
            MainListFragment.MAIN_LIST_ENTITY, MainListEntity::class.java
        ) { mainListEntity ->
            image_view.load(mainListEntity.imageUrl) { crossfade(true) }
            text_view_title.text = mainListEntity.title
            text_view_introduction.text = mainListEntity.introduction
            showView()
        }
    }

    private fun showView() {
        image_view.visibility = View.VISIBLE
        text_view_title.visibility = View.VISIBLE
        text_view_introduction.visibility = View.VISIBLE
    }

    private fun hideView() {
        image_view.visibility = View.INVISIBLE
        text_view_title.visibility = View.INVISIBLE
        text_view_introduction.visibility = View.INVISIBLE
    }

    /**
     * 判斷此Fragment目前屬於顯示還是隱藏
     * 第二次顯示之後, 每次都會觸發此方法; 第一次顯示, 只會觸發onCreateView
     */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            setActivityCurrentShowFragmentLabel()
            hideView()
        }
    }
}