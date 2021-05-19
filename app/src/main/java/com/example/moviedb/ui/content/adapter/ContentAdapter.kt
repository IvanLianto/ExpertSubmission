package com.example.moviedb.ui.content.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedb.R
import com.example.moviedb.core.databinding.ItemListBinding
import com.example.moviedb.core.domain.model.DataModel
import com.example.moviedb.interfaces.DataInterface
import com.example.moviedb.utils.LINK_IMAGE_MOVIEDB

class ContentAdapter :
    ListAdapter<DataModel, ContentAdapter.ContentViewHolder>(DiffCallback()) {

    var dataInterface: DataInterface? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        return ContentViewHolder(
            ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data, dataInterface)
        }
    }

    inner class ContentViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataModel, dataInterface: DataInterface?) {
            binding.apply {
                Glide.with(root)
                    .load("${LINK_IMAGE_MOVIEDB}${data.imgPoster}")
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
                    .into(ivPoster)
                tvRate.text = data.score
                tvTitle.text = data.title
                layoutRoot.setOnClickListener {
                    dataInterface?.onClicked(
                        root,
                        data
                    )
                }
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<DataModel>() {
    override fun areItemsTheSame(oldItem: DataModel, newItem: DataModel): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: DataModel, newItem: DataModel): Boolean =
        oldItem.title == newItem.title
}
