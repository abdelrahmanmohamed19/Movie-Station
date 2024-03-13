package com.moviestation.moviestation.domain.repository

import com.moviestation.moviestation.data.remote.dto.CategoriesDto
import com.moviestation.moviestation.data.remote.dto.MoviesDto

interface MoviesRepository {

    suspend fun getMovieCategories() : CategoriesDto

    suspend fun getMovies (id : Int) : MoviesDto
}