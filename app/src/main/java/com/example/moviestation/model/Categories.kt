package com.example.moviestation.model

import com.google.gson.annotations.SerializedName

data class Categories(
    @SerializedName("genres")
    val categorie : List<Categorie>
)

data class Categorie(
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String
)
