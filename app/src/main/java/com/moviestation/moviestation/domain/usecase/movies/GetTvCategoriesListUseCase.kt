package com.moviestation.moviestation.domain.usecase.movies

import com.moviestation.moviestation.data.remote.dto.Tv
import com.moviestation.moviestation.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetTvCategoriesListUseCase @Inject constructor(private val repo : MoviesRepository)  {

    suspend fun getTvCategoriesList(id : Int) : StateFlow<List<Tv>> {
        return repo.getTvCategorieList(id)
    }
}