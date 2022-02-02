package com.example.cattest.data.util

import com.example.cattest.data.local.entity.FavoriteCatEntity
import com.example.cattest.data.remote.response.CatResponse
import com.example.cattest.domain.model.CatImage

fun FavoriteCatEntity.toUi() =
    CatImage(
        this.catId,
        this.catImageUrl,
        this.width,
        this.height
    )

fun CatImage.toDatabase() = FavoriteCatEntity(
    this.catId,
    this.imageUrl,
    this.width,
    this.height
)

fun CatResponse.toUi() =
    CatImage(
        this.id,
        this.imageUrl,
        this.imageWidth,
        this.imageHeight
    )