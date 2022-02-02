package com.example.cattest.ui.cats.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cattest.data.remote.response.CatResponse
import com.example.cattest.data.util.toUi
import com.example.cattest.databinding.LayoutCatItemBinding
import com.example.cattest.domain.model.CatImage

class CatPageAdapter(
    private val onAddButtonClick: (CatImage) -> Unit,
    private val onDownloadClick: (String) -> Unit
) : PagingDataAdapter<CatImage, CatPageAdapter.ViewHolder>(REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<CatImage>() {
            override fun areItemsTheSame(oldItem: CatImage, newItem: CatImage): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: CatImage, newItem: CatImage): Boolean =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutCatItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    inner class ViewHolder(private val binding: LayoutCatItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cat: CatImage) = with(binding) {
            favoriteButton.setOnClickListener {
                onAddButtonClick(cat)
            }

            downloadButton.setOnClickListener {
                onDownloadClick(cat.imageUrl)
            }
            Glide.with(root)
                .load(cat.imageUrl)
                .into(catImageView)
        }
    }
}