package com.moviestation.moviestation.domain.usecase.home

import com.moviestation.moviestation.data.remote.dto.Movies
import com.moviestation.moviestation.domain.repository.HomeRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetTrendingMoviesUseCase @Inject constructor(private val repo : HomeRepository) {
    suspend fun getTrendingMovies () : StateFlow<List<Movies>> {
        return repo.getTrendingMovies()
    }
}