package com.moviestation.moviestation.data.repositories

import com.example.moviestation.domain.repositories.TvRepositorey
import com.moviestation.moviestation.data.api.ApiService
import com.moviestation.moviestation.data.model.Categories
import com.moviestation.moviestation.data.model.Tv
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TvRepositoreyImpl @Inject constructor (private val api : ApiService, private val apiKey : String ) :TvRepositorey {

    override suspend fun getTvCategories() : StateFlow<List<Categories>> {

        val categorieList = MutableStateFlow(emptyList<Categories>())

        val response = api.getTvCategorie(apiKey)

        if (response.isSuccessful){
            val responseBody = response.body()?.categoriesList
            categorieList.value = responseBody !!
        }
        return categorieList
    }

    override suspend fun getTvCategorieList(id : Int) : StateFlow<List<Tv>>{
        val categorieList = MutableStateFlow(emptyList<Tv>())
        val response = api.getTvCategorieList(apiKey,id)
        if (response.isSuccessful){
            val responseBody = response.body()?.trendingTv
            categorieList.value = responseBody !!
        }
        return categorieList
    }
}