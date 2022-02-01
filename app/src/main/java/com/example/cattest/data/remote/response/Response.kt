package com.example.cattest.data.remote.response

import com.google.gson.annotations.SerializedName

data class Response(
    val cats: List<CatResponse>
)

data class CatResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val imageUrl: String,
    @SerializedName("width")
    val imageWidth: Int,
    @SerializedName("height")
    val imageHeight: Int
)