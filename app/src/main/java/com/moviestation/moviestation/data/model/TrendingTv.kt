package com.moviestation.moviestation.data.model

import com.google.gson.annotations.SerializedName


data class TrendingTvResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val trendingTv: List<Tv>,
)

data class Tv(
    @SerializedName("name")
    val name: String,
    @SerializedName("poster_path")
    val poster: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("overview")
    val overView: String
)
