package com.example.moviestation.model

import com.google.gson.annotations.SerializedName


data class TrendingTvResponse(
    @SerializedName("page")
    val page : Int,
    @SerializedName("results")
    val TrendingTv: List<TrendingTv>,
)

data class TrendingTv(
    @SerializedName("name")
    val TvName: String,
    @SerializedName("poster_path")
    val poster: String
)
