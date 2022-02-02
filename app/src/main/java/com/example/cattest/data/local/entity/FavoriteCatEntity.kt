package com.example.cattest.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteCatEntity(
    @PrimaryKey
    val catId: String,
    val catImageUrl: String,
    val width: Int,
    val height: Int
)