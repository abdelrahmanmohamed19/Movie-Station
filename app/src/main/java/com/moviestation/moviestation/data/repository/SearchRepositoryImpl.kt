package com.moviestation.moviestation.data.repository

import com.moviestation.moviestation.domain.repository.SearchRepository
import com.moviestation.moviestation.data.remote.ApiService
import com.moviestation.moviestation.data.remote.dto.SearchedItemDto
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val apiService : ApiService, private val apiKey : String) : SearchRepository {
    override suspend fun getSearchedItem(searchedItem: String): SearchedItemDto {
        return apiService.getSearchedItem(apiKey, searchedItem)
    }
}