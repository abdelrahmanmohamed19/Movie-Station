package com.moviestation.moviestation.domain.usecase.movies

import com.moviestation.moviestation.domain.repository.MoviesRepository
import com.moviestation.moviestation.data.remote.dto.Categories
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetMovieCategoriesUseCase @Inject constructor(private val repo : MoviesRepository)  {

    suspend fun getMovieCategories() : StateFlow<List<Categories>> {
        return repo.getMovieCategories()
    }
}