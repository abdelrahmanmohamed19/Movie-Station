package com.moviestation.moviestation.data.repositories

import com.example.moviestation.domain.repositories.SearchRepositorey
import com.moviestation.moviestation.data.api.ApiService
import com.moviestation.moviestation.data.model.Tv
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class SearchRepositoreyImpl @Inject constructor(private val api : ApiService, private val apiKey : String) : SearchRepositorey {

    override suspend fun getSearchedItem(item : String) : StateFlow<List<Tv>> {

        val SearchedItem = MutableStateFlow(emptyList<Tv>())

        val response = api.getSearchedItem(apiKey,item)

        if (response.isSuccessful){
            val responseBody = response.body()?.trendingTv
            SearchedItem.value = responseBody !!
        }
        return SearchedItem
    }
}