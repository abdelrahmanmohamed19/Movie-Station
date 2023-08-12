package com.moviestation.moviestation.data.repositories

import android.util.Log
import com.example.moviestation.domain.repositories.MoviesRepositorey
import com.moviestation.moviestation.data.api.ApiService
import com.moviestation.moviestation.data.model.Categories
import com.moviestation.moviestation.data.model.Movies
import com.moviestation.moviestation.data.model.Tv
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MoviesRepositoreyImpl @Inject constructor(private val api : ApiService, private val apiKey : String ) : MoviesRepositorey{

    override suspend fun getMovieCategories() : StateFlow<List<Categories>>{

        val categorieList = MutableStateFlow(emptyList<Categories>())

        val response = api.getMoviesCategorie(apiKey)

        if (response.isSuccessful){
            val responseBody = response.body()?.categoriesList
            categorieList.value = responseBody !!
        }
        return categorieList
    }



    override suspend fun getMoviesCategorieList(id : Int) : StateFlow<List<Movies>>{
        val categoriesList = MutableStateFlow(emptyList<Movies>())
        val response = api.getMovieCategorieList(apiKey,id)
        if (response.isSuccessful){
            val responseBody = response.body()?.trendingMovies
            categoriesList.value = responseBody !!
        }
        Log.i("Repoooo",categoriesList.value.toString())
        return categoriesList
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