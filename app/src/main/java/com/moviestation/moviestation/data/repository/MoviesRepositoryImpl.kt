package com.moviestation.moviestation.data.repository

import com.moviestation.moviestation.domain.repository.MoviesRepository
import com.moviestation.moviestation.data.remote.ApiService
import com.moviestation.moviestation.data.remote.dto.CategoriesDto
import com.moviestation.moviestation.data.remote.dto.MoviesDto
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val apiService : ApiService,
    private val apiKey : String
) : MoviesRepository {

    override suspend fun getMovieCategories(): CategoriesDto {
        return apiService.getMovieCategories(apiKey)
    }

    override suspend fun getMovies(id: Int): MoviesDto {
        return apiService.getMovies(apiKey,id)
    }

}