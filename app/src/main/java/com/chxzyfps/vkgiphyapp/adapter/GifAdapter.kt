package com.chxzyfps.vkgiphyapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chxzyfps.vkgiphyapp.R
import com.chxzyfps.vkgiphyapp.data.GifItem
import com.chxzyfps.vkgiphyapp.databinding.GifItemBinding

class GifAdapter(private val listener: OnItemClickListener) : ListAdapter<GifItem, GifAdapter.Holder>(Comparator()) {

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        private val view = itemView
        private val binding = GifItemBinding.bind(itemView)
        fun bind(gifItem: GifItem) {
            val gifUrl = "https://media4.giphy.com/media/${gifItem.id}/giphy.gif"
            binding.apply {
                Glide.with(view.context)
                    .load(gifUrl)
                    .fitCenter()
                    .into(ivGif)
                cardView.startAnimation(AnimationUtils.loadAnimation(itemView.context, R.anim.anim_rc_view))
            }
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    class Comparator : DiffUtil.ItemCallback<GifItem>() {
        override fun areItemsTheSame(oldItem: GifItem, newItem: GifItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GifItem, newItem: GifItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gif_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
}