package com.moviestation.moviestation.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("genres")
    val categoriesList : List<Categories>
)

data class Categories(
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String
)
