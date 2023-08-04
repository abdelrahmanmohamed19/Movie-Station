package com.example.moviestation.data.repositories

import com.example.moviestation.data.api.ApiService
import com.example.moviestation.model.TrendingMovies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class SearchRepositorey @Inject constructor(private val api : ApiService ,private val apiKey : String) {

    suspend fun getSearchedItem(item : String) : StateFlow<List<TrendingMovies>> {

        val SearchedItem = MutableStateFlow(emptyList<TrendingMovies>())

        val response = api.getSearchedItem(apiKey,item)

        if (response.isSuccessful){
            val responseBody = response.body()?.TrendingMovies
            SearchedItem.value = responseBody !!
        }
        return SearchedItem
    }
}