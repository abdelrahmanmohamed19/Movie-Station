package com.moviestation.moviestation.domain.usecase.search

import com.moviestation.moviestation.domain.repository.SearchRepository
import com.moviestation.moviestation.data.remote.dto.Tv
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repo : SearchRepository){

    suspend fun getSearchedItem(item : String) : StateFlow<List<Tv>> {
        return repo.getSearchedItem(item)
    }
}