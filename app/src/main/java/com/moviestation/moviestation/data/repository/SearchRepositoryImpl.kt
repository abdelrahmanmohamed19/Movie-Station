package com.moviestation.moviestation.data.repository

import com.moviestation.moviestation.domain.repository.SearchRepository
import com.moviestation.moviestation.data.remote.ApiService
import com.moviestation.moviestation.data.remote.dto.Tv
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val api : ApiService, private val apiKey : String) :
    SearchRepository {

    override suspend fun getSearchedItem(item : String) : StateFlow<List<Tv>> {

        val searchedItem = MutableStateFlow(emptyList<Tv>())

        val response = api.getSearchedItem(apiKey,item)

        if (response.isSuccessful){
            val responseBody = response.body()?.trendingTv
            searchedItem.value = responseBody !!
        }
        return searchedItem
    }
}