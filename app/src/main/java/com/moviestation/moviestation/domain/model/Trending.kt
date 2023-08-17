package com.moviestation.moviestation.domain.model

data class Trending( val name : String, val image : String? = "", val voteAverage : Double = 0.0 , val overView : String = "" )