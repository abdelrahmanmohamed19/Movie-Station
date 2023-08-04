package com.example.moviestation.data.repositories

import android.util.Log
import com.example.moviestation.data.api.ApiService
import com.example.moviestation.model.Categorie
import com.example.moviestation.model.TrendingMovies
import com.example.moviestation.model.TrendingTv
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TvRepositorey @Inject constructor (private val api : ApiService, private val apiKey : String ) {

    suspend fun getTvCategories() : StateFlow<List<Categorie>> {

        val categorieList = MutableStateFlow(emptyList<Categorie>())

        val response = api.getTvCategorie(apiKey)

        if (response.isSuccessful){
            val responseBody = response.body()?.categorie
            categorieList.value = responseBody !!
        }
        return categorieList
    }

    suspend fun getTvCategorieList(id : Int) : StateFlow<List<TrendingTv>>{
        val categorieList = MutableStateFlow(emptyList<TrendingTv>())
        val response = api.getTvCategorieList(apiKey,id)
        if (response.isSuccessful){
            val responseBody = response.body()?.TrendingTv
            categorieList.value = responseBody !!
        }
        return categorieList
    }
}