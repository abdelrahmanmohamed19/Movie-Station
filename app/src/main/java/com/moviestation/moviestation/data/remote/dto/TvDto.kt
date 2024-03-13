package com.moviestation.moviestation.data.remote.dto

import com.google.gson.annotations.SerializedName


data class TvDto(
    @SerializedName("results")
    val trendingTvShow: List<TvShow>
)

data class TvShow(
    @SerializedName("name")
    val name: String,
    @SerializedName("poster_path")
    val poster: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("overview")
    val overView: String
)
