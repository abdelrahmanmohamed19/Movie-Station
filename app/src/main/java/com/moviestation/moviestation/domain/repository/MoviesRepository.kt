package com.moviestation.moviestation.domain.repository

import com.moviestation.moviestation.data.remote.dto.Categories
import com.moviestation.moviestation.data.remote.dto.Movies
import com.moviestation.moviestation.data.remote.dto.Tv
import kotlinx.coroutines.flow.StateFlow

interface MoviesRepository {

    suspend fun getMovieCategories() : StateFlow<List<Categories>>

    suspend fun getMoviesCategorieList(id : Int) : StateFlow<List<Movies>>

    suspend fun getTvCategorieList(id : Int) : StateFlow<List<Tv>>
}