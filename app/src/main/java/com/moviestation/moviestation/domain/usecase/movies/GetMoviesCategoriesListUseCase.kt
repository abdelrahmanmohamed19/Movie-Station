package com.moviestation.moviestation.domain.usecase.movies

import com.moviestation.moviestation.data.remote.dto.Movies
import com.moviestation.moviestation.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetMoviesCategoriesListUseCase  @Inject constructor(private val repo : MoviesRepository)  {

    suspend fun getMoviesCategoriesList(id : Int) : StateFlow<List<Movies>> {
        return repo.getMoviesCategorieList(id)
    }
}