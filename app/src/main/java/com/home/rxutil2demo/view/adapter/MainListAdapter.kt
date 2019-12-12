package com.home.rxutil2demo.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.home.rxutil2demo.R
import com.home.rxutil2demo.model.entity.MainListEntity
import kotlinx.android.synthetic.main.fragment_main_detail_recycler_view_item.view.*

class MainListAdapter : RecyclerView.Adapter<MainListAdapter.ViewHolder>() {

    private val data = mutableListOf<MainListEntity>()
    var onItemClick: (MainListEntity) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(
            R.layout.fragment_main_detail_recycler_view_item, parent, false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data[position].apply {
            holder.itemView.constraint_layout_root.setOnClickListener {
                onItemClick(this)
            }
            holder.itemView.image_view.load(this.imageUrl) {
                crossfade(true)
                transformations(CircleCropTransformation())
            }
            holder.itemView.text_view_title.text = this.title
            holder.itemView.text_view_introduction.text = this.introduction
        }
    }

    override fun getItemCount() = data.size

    fun updateData(data: MutableList<MainListEntity>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}