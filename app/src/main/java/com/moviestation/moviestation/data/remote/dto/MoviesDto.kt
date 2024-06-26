package com.moviestation.moviestation.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MoviesDto(
    @SerializedName("results")
    val trendingMovies : List<Movie>
)

data class Movie(
    @SerializedName("title")
    val name: String,
    @SerializedName("poster_path")
    val poster: String,
    @SerializedName("vote_average")
    val voteAverage : Double,
    @SerializedName("overview")
    val overView : String
)
