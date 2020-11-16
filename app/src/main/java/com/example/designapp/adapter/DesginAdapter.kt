package com.example.designapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.designapp.R
import kotlinx.android.synthetic.main.item_view.view.*

class DesginAdapter : RecyclerView.Adapter<DesginAdapter.DesignViewHolder>() {
    class DesignViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    private val diffCallBack = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem

        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }
    private val diff = AsyncListDiffer(this, diffCallBack)
    var list: List<String>
        get() = diff.currentList
        set(value) = diff.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DesignViewHolder {
        return DesignViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        )
    }

    private var onItemClickListener: ((String,View) -> Unit) ?= null

    fun setOnItemClickListener(listener: (String , View) -> Unit) {
        onItemClickListener = listener
    }

    override fun onBindViewHolder(holder: DesignViewHolder, position: Int) {
        val url = list[position]
        holder.itemView.apply {

              holder.itemView.imageView2.setOnClickListener {
                 onItemClickListener?.let {click->
                 click(url,it)
                 }

              }
            }
        }

    override fun getItemCount(): Int {

        return list.size
    }





}
