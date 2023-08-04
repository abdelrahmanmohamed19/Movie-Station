package com.example.moviestation.data.repositories

import android.util.Log
import com.example.moviestation.data.api.ApiService
import com.example.moviestation.model.Categorie
import com.example.moviestation.model.Categories
import com.example.moviestation.model.TrendingMovies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MovieRepositorey @Inject constructor(private val api : ApiService , private val apiKey : String ) {

    suspend fun getMovieCategories() : StateFlow<List<Categorie>>{

        val categorieList = MutableStateFlow(emptyList<Categorie>())

        val response = api.getMoviesCategorie(apiKey)

        if (response.isSuccessful){
            val responseBody = response.body()?.categorie
            categorieList.value = responseBody !!
        }
        return categorieList
    }

    suspend fun getMovieCategorieList(id : Int) : StateFlow<List<TrendingMovies>>{
        val categorieList = MutableStateFlow(emptyList<TrendingMovies>())
        val response = api.getMovieCategorieList(apiKey,id)
        if (response.isSuccessful){
            val responseBody = response.body()?.TrendingMovies
            categorieList.value = responseBody !!
        }
        return categorieList
    }
}