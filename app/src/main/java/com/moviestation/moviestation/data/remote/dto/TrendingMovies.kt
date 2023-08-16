package com.moviestation.moviestation.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TrendingMoviesResponse(
    @SerializedName("page")
    val page : Int,
    @SerializedName("results")
    val trendingMovies : List<Movies>,
)

data class Movies(
    @SerializedName("title")
    val name: String,
    @SerializedName("poster_path")
    val poster: String,
    @SerializedName("vote_average")
    val voteAverage : Double,
    @SerializedName("overview")
    val overView : String
)
