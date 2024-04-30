package com.moviestation.moviestation.presentation.movies

import com.moviestation.moviestation.data.remote.dto.Categories
import com.moviestation.moviestation.domain.model.Trending

data class MoviesUIState(
    var moviesCategoriesList: List<Categories> = emptyList(),
    var moviesList : List<Trending> = emptyList(),
    var isLoading: Boolean = false,
    var errorMessage: String = ""
)
