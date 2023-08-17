package com.moviestation.moviestation.domain.usecase.tv

import com.moviestation.moviestation.data.remote.dto.Tv
import com.moviestation.moviestation.domain.repository.TvRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetTvCategoriesListUseCase @Inject constructor(private val repo : TvRepository){

    suspend fun getTvCategoriesList(id : Int) : StateFlow<List<Tv>> {
        return repo.getTvCategorieList(id)
    }
}