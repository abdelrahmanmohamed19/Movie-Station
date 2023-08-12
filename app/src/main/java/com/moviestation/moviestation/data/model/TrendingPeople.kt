package com.moviestation.moviestation.data.model

import com.google.gson.annotations.SerializedName


data class TrendingPeopleResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val trendingPeople: List<People>,
)


data class People(
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_path")
    val poster: String
)


