package com.moviestation.moviestation.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SearchedItemDto (
    val results: List<Item>
)

data class Item (
    @SerializedName("name")
    val name: String,
    @SerializedName("poster_path")
    val poster: String,
    @SerializedName("overview")
    val overView: String
)