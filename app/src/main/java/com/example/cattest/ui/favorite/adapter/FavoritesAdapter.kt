package com.example.cattest.ui.favorite.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cattest.R
import com.example.cattest.databinding.LayoutCatItemBinding
import com.example.cattest.domain.model.CatImage

class FavoritesAdapter(
    private val onDeleteClick: (String) -> Unit
) : RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    var catImages = listOf<CatImage>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutCatItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(catImages[position])
    }

    override fun getItemCount() = catImages.size

    inner class ViewHolder(private val binding: LayoutCatItemBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {

        fun bind(catImage: CatImage) = with(binding) {
            favoriteButton.setText(R.string.delete_from_favorite)
            favoriteButton.setOnClickListener {
                onDeleteClick(catImage.catId)
            }
            Glide.with(root)
                .load(catImage.imageUrl)
                .into(catImageView)
        }
    }
}