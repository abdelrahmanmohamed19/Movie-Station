package com.example.moviestation.model

import com.google.gson.annotations.SerializedName


data class TrendingPeopleResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val TrendingPeople: List<TrendingPeople>,
)


data class TrendingPeople(
    @SerializedName("name")
    val Name: String,
    @SerializedName("profile_path")
    val Image: String
)


