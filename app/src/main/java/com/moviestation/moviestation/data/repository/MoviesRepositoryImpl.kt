package com.moviestation.moviestation.data.repository

import com.moviestation.moviestation.domain.repository.MoviesRepository
import com.moviestation.moviestation.data.remote.ApiService
import com.moviestation.moviestation.data.remote.dto.Categories
import com.moviestation.moviestation.data.remote.dto.Movies
import com.moviestation.moviestation.data.remote.dto.Tv
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val api : ApiService, private val apiKey : String ) :
    MoviesRepository {

    override suspend fun getMovieCategories() : StateFlow<List<Categories>>{

        val categoriesList = MutableStateFlow(emptyList<Categories>())

        val response = api.getMoviesCategorie(apiKey)

        if (response.isSuccessful){
            val responseBody = response.body()?.categoriesList
            categoriesList.value = responseBody !!
        }
        return categoriesList
    }



    override suspend fun getMoviesCategorieList(id : Int) : StateFlow<List<Movies>>{
        val categoriesList = MutableStateFlow(emptyList<Movies>())
        val response = api.getMovieCategorieList(apiKey,id)
        if (response.isSuccessful){
            val responseBody = response.body()?.trendingMovies
            categoriesList.value = responseBody !!
        }
        return categoriesList
    }

    override suspend fun getTvCategorieList(id : Int) : StateFlow<List<Tv>>{
        val categoriesList = MutableStateFlow(emptyList<Tv>())
        val response = api.getTvCategorieList(apiKey,id)
        if (response.isSuccessful){
            val responseBody = response.body()?.trendingTv
            categoriesList.value = responseBody !!
        }
        return categoriesList
    }
}