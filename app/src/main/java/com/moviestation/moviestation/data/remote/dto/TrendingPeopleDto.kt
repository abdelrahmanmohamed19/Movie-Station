package com.moviestation.moviestation.data.remote.dto

import com.google.gson.annotations.SerializedName


data class TrendingPeopleDto(
    @SerializedName("results")
    val trendingPeople: List<People>
)


data class People(
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_path")
    val poster: String
)


