package com.moviestation.moviestation.domain.repository

import com.moviestation.moviestation.data.remote.dto.Categories
import com.moviestation.moviestation.data.remote.dto.Tv
import kotlinx.coroutines.flow.StateFlow

interface TvRepository {

    suspend fun getTvCategories() : StateFlow<List<Categories>>

    suspend fun getTvCategorieList(id : Int) : StateFlow<List<Tv>>
}