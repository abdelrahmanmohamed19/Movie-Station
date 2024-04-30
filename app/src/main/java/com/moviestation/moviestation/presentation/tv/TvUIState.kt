package com.moviestation.moviestation.presentation.tv

import com.moviestation.moviestation.data.remote.dto.Categories
import com.moviestation.moviestation.domain.model.Trending

data class TvUIState(
    var tvShowCategoriesList: List<Categories> = emptyList(),
    var tvShowsList : List<Trending> = emptyList(),
    var isLoading: Boolean = false,
    var errorMessage: String = ""
)
