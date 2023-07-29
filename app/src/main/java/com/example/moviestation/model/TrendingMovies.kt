package com.example.moviestation.model

import com.google.gson.annotations.SerializedName

data class TrendingMoviesResponse(
    @SerializedName("page")
    val page : Int,
    @SerializedName("results")
    val TrendingMovies : List<TrendingMovies>,
)

data class TrendingMovies(
    @SerializedName("title")
    val name: String,
    @SerializedName("poster_path")
    val poster: String
)
