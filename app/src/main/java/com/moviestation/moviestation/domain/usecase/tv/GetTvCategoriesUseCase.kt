package com.moviestation.moviestation.domain.usecase.tv

import com.moviestation.moviestation.domain.repository.TvRepository
import com.moviestation.moviestation.data.remote.dto.Categories
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetTvCategoriesUseCase @Inject constructor(private val repo : TvRepository){

    suspend fun getTvCategories() : StateFlow<List<Categories>> {
        return repo.getTvCategories()
    }

}